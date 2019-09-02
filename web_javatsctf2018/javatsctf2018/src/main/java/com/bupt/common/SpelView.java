package com.bupt.common;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bupt.domain.Profile;
import org.springframework.context.expression.MapAccessor;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.SpelParseException;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.PropertyPlaceholderHelper;
import org.springframework.util.PropertyPlaceholderHelper.PlaceholderResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * Simple String template renderer.
 *
 */
public class SpelView implements View {

    private String template;

    private String prefix;

    private final SpelExpressionParser parser = new SpelExpressionParser();

    private final StandardEvaluationContext context = new StandardEvaluationContext();

    private PlaceholderResolver resolver;

    private Profile profile;

    private String elstr;

    public SpelView(Profile profile) {
        this.profile = profile;
        this.elstr = resolvePlaceholder(profile.getName()) == null ? profile.getName() : resolvePlaceholder(profile.getName());
        this.template = createTemplate(this.elstr);
    }

    public String getTemplate(){
        return this.template;
    }

    public String resolvePlaceholder(String name){
        try {
            Expression expression = parser.parseExpression(name);
            String value = expression.getValue(String.class);
            return value == null ? null : value.toString();
        }
        catch (Exception e){
            return null;
        }
    }

    protected String createTemplate(String elexpression) {
        String template = TEMPLATE;

        if (!elexpression.isEmpty()) {
            template = template.replace("%name%", elexpression);
        }
        else {
            template = template.replace("%name%", "");
        }
        return template;
    }

    private static String TEMPLATE = "<div class=\"col-sm-9 input-group\"><input type=\"text\" name=\"name\" value=\"%name%\" class=\"form-control\"><span class=\"input-group-addon\"><span class=\"glyphicon glyphicon-user\"></span></span></div>";


    public String getContentType() {
        return "text/html";
    }

    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Map<String, Object> map = new HashMap<String, Object>(model);
        String path = ServletUriComponentsBuilder.fromContextPath(request).build()
                .getPath();
        map.put("path", (Object) path==null ? "" : path);
        context.setRootObject(map);
        String maskedTemplate = template.replace("${", prefix);
        PropertyPlaceholderHelper helper = new PropertyPlaceholderHelper(prefix, "}");
        String result = helper.replacePlaceholders(maskedTemplate, resolver);
        result = result.replace(prefix, "${");
        response.setContentType(getContentType());
        response.getWriter().append(result);
    }

}