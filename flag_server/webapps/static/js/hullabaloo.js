/**
 * hullabaloo v 0.3
 *
 */
(function(root, factory) {
  if (typeof define === 'function' && define.amd) {
    define(['buoy'], factory(root));
  } else if (typeof exports === 'object') {
    module.exports = factory(require('buoy'));
  } else {
    root.hullabaloo = factory(root, root.buoy);
  }
})(typeof global !== 'undefined' ? global : this.window || this.global, function(root) {
  var init = function(root) {

    var hullabaloo = function() {
      // Объект создаваемый сейчас.
      // генерируется в this.generate()
      this.hullabaloo = {};

      // Массив с объектами активных алертов
      this.hullabaloos = [];

      this.success = false;

      // Дополнительные настройки дял алерта
      this.options = {
        ele: "body",
        offset: {
          from: "top",
          amount: 20
        },
        align: "right",
        width: 250,
        delay: 5000,
        allow_dismiss: true,
        stackup_spacing: 10,
        text: "Произошла неизвестная ошибка.",
        icon: {
          success: "fa fa-check-circle",
          info: "fa fa-info-circle",
          warning: "fa fa-life-ring",
          danger: "fa fa-exclamation-circle",
          light: "fa fa-sun",
          dark: "fa fa-moon"
        },
        status: "danger",
        alertClass: "", // Дополнительные класс для блока алерта
        fnStart: false, // Ф-ия будет выполняться при старте
        fnEnd: false, // Ф-ия будет выполняться по завершинию
        fnEndHide: false, // Ф-ия будет выполняться после закрытия сообщения

      };
    };

    // Выводим сообщение
    hullabaloo.prototype.send = function(text, status) {
      // Запустим функцию при старте
      if (typeof this.options.fnStart == "function")
        this.options.fnStart();

      // Ссылка на объект
      var self = this;
      // Флаг для обозначение что найденна группа одинаковых алертов
      var flag = 1;
      // Счетчик для переборки всех алертов. Поиск одинаковых
      var i = +this.hullabaloos.length - 1;
      // Главный алерта если уже есть такие же алерты
      var parent;

      // Сгенерируем сообщение
      var hullabaloo = this.generate(text, status);

      // Проверим нет ли уже таких же сообщений
      if (this.hullabaloos.length) {
        // Пройдем до конца массива алертов, пока не найдем совпадение
        while (i >= 0 && flag) {
          // Если у нас присутствуют одинаковые сообщения (сгруппируем их)
          if (this.hullabaloos[i].text == hullabaloo.text && this.hullabaloos[i].status == hullabaloo.status) {
            // Запомним главный алерт
            parent = this.hullabaloos[i];
            // Флаг выхода из цикла
            flag = 0;

            // Переместим наш алерт на место гланого со смещением
            hullabaloo.elem.css(this.options.offset.from, parseInt(parent.elem.css(this.options.offset.from)) + 4);
            hullabaloo.elem.css(this.options.align, parseInt(parent.elem.css(this.options.align)) + 4);
          }
          i--;
        }
      }

      // Проверяем, группа алертов у нас или только один
      if (typeof parent == 'object') {
        // Если алерт в группе то добавим его в группу и обнулим счетчик группы
        clearTimeout(parent.timer);
        // Зададим новый счетчик для группы
        parent.timer = setTimeout(function() {
          self.closed(parent);
        }, this.options.delay);
        hullabaloo.parent = parent;
        // присвоим наш алерт в группу к родителю
        parent.hullabalooGroup.push(hullabaloo);
        // Если алер один
      } else {
        // Запомним позицию алерта, понадобиться для перемещения алертов вверх
        hullabaloo.position = parseInt(hullabaloo.elem.css(this.options.offset.from));

        // Активируем таймер
        hullabaloo.timer = setTimeout(function() {
          self.closed(hullabaloo);
        }, this.options.delay);
        // Добавим алерт в общий массив алертов
        this.hullabaloos.push(hullabaloo);
      }

      // Покажем алерт пользователю
      hullabaloo.elem.fadeIn();

      // Запустим функцию по завершения
      if (typeof this.options.fnEnd == "function")
        this.options.fnEnd();
    }

    // Закрывает алерт
    hullabaloo.prototype.closed = function(hullabaloo) {
      var self = this;
      var idx, i, move, next;

      if("parent" in hullabaloo){
        hullabaloo = hullabaloo.parent;
      }

      // проверяем есть ли массив с алертами
      if (this.hullabaloos !== null) {
        // Найдем в массиве закрываемый алерт
        idx = $.inArray(hullabaloo, this.hullabaloos);
        if(idx == -1) return;

        // Если это група алертов, то закроем все
        if (!!hullabaloo.hullabalooGroup && hullabaloo.hullabalooGroup.length) {
          for (i = 0; i < hullabaloo.hullabalooGroup.length; i++) {
            // закрыть алерт
            $(hullabaloo.hullabalooGroup[i].elem).remove();
          }
        }

        // Закрываем наш алерт
        $(this.hullabaloos[idx].elem).fadeOut("slow", function(){
          this.remove();
        });

        if (idx !== -1) {
          next = idx + 1;
          // Если в массиве есть другие алерты, поднимем их на место закрытого
          if (this.hullabaloos.length > 1 && next < this.hullabaloos.length) {
            // Отнимаем верхнюю гранизу закрытого алерта от верхней границы следующего алерта
            // и расчитываем на сколько двигать все алерты
            move = this.hullabaloos[next].position - this.hullabaloos[idx].position;

            // двигаем все алерты, которые идут за закрытым
            for (i = idx; i < this.hullabaloos.length; i++) {
              this.animate(self.hullabaloos[i], parseInt(self.hullabaloos[i].position) - move);
              self.hullabaloos[i].position = parseInt(self.hullabaloos[i].position) - move
            }
          }

          // Удалим закрытый алерт из массива с алертами
          this.hullabaloos.splice(idx, 1);

          // Запустим функцию после закрытия сообщения
          if (typeof this.options.fnEndHide == "function")
            this.options.fnEndHide();
        }
      }
    }


    // Анимация для подъема алертов вверх
    hullabaloo.prototype.animate = function(hullabaloo, move) {
      var self = this;
      var timer,
        position, // Верх алерта, который тащим
        i, // Счетчик для перебора группы алертов
        group = 0; // Обозначение, группа алертов или одиночный

      // Верх / Низ алерта, который тащим
      position = parseInt(hullabaloo.elem.css(self.options.offset.from));
      // Если это группа алертов
      group = hullabaloo.hullabalooGroup.length;

      // Запустим таймер
      timer = setInterval(frame, 2);
      // Ф-ия для таймера
      function frame() {
        if (position == move) {
          clearInterval(timer);
        } else {
          position--;
          hullabaloo.elem.css(self.options.offset.from, position);

          // Если это группа алертов
          if (group) {
            for (i = 0; i < group; i++) {
              hullabaloo.hullabalooGroup[i].elem.css(self.options.offset.from, position + 5);
            }
          }
        }
      }
    }


    // Генерация алерта на странице
    hullabaloo.prototype.generate = function(text, status) {
      var alertsObj = {
        icon: "", // Иконка
        status: status || this.options.status, // Статус
        text: text || this.options.text, // Тект
        elem: $("<div>"), // HTML код самого алерта

        // Группировка одинаковых алертов
        hullabalooGroup: []
      };
      var option, // Настройки алерта
          offsetAmount, // Отступы алерта
          css; // CSS свойства алерта
          self = this;

      option = this.options;

      // Добавим дополнительный класс
      alertsObj.elem.attr("class", "hullabaloo alert "+option.alertClass);

      // Статус
      alertsObj.elem.addClass("alert-" + alertsObj.status);

      // Кнопка закрытия сообщения
      if (option.allow_dismiss) {
        alertsObj.elem.addClass("alert-dismissible");
        alertsObj.elem.append("<button class=\"close\" type=\"button\" id=\"hullabalooClose\" aria-label=\"Close\"><span aria-hidden=\"true\">&times;</span></button>");
        $( "#hullabalooClose", $(alertsObj.elem) ).bind( "click", function(){
          self.closed(alertsObj);
        });
      }

      // Icon
      switch (alertsObj.status) {
        case "success":
          alertsObj.icon = option.icon.success;
          break;
        case "info":
          alertsObj.icon = option.icon.info;
          break;
        case "danger":
          alertsObj.icon = option.icon.danger;
          break;
        case "light":
          alertsObj.icon = option.icon.light;
          break;
        case "dark":
          alertsObj.icon = option.icon.dark;
          break;
        default:
          alertsObj.icon = option.icon.warning;
      }

      // Добавим текст в сообщение
      alertsObj.elem.append("<i class=\"" + alertsObj.icon + "\"></i> " + alertsObj.text);

      // Присвоим отступ от верха
      offsetAmount = option.offset.amount;
      // Если есть другие алерты то прибавим к отступу их высоту
      $(".hullabaloo").each(function() {
        return offsetAmount = Math.max(offsetAmount, parseInt($(this).css(option.offset.from)) + $(this).outerHeight() + option.stackup_spacing);
      });

      // Добавим CSS стили
      css = {
        "position": (option.ele === "body" ? "fixed" : "absolute"),
        "margin": 0,
        "z-index": "9999",
        "display": "none"
      };
      css[option.offset.from] = offsetAmount + "px";
      alertsObj.elem.css(css);

      if (option.width !== "auto") {
        alertsObj.elem.css("width", option.width + "px");
      }
      $(option.ele).append(alertsObj.elem);
      switch (option.align) {
        case "center":
          alertsObj.elem.css({
            "left": "50%",
            "margin-left": "-" + (alertsObj.elem.outerWidth() / 2) + "px"
          });
          break;
        case "left":
          alertsObj.elem.css("left", "20px");
          break;
        default:
          alertsObj.elem.css("right", "20px");
      }

      return alertsObj;
    };

    return hullabaloo;
  };
  return init(root);
});
