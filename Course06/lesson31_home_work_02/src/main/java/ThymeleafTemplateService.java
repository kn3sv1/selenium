import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.util.HashMap;
import java.util.Map;

public class ThymeleafTemplateService {
    TemplateEngine engine;
    MenuService menu;

    public ThymeleafTemplateService() {
        this.menu = new MenuService();
        // 1. Template resolver (loads HTML from classpath folder)
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        //Path cannot use because we will have conflict with Prefix folder path
        resolver.setPrefix("thymeleaf-templates/"); // folder inside resources
        resolver.setSuffix(".html");
        resolver.setTemplateMode("HTML");
        resolver.setCharacterEncoding("UTF-8");
        resolver.setCacheable(false);

        // 2. Template engine using that resolver
        this.engine = new TemplateEngine();
        engine.setTemplateResolver(resolver);
        // 🔴 REQUIRED: register layout dialect
        engine.addDialect(new LayoutDialect());
    }

    public String renderTemplate(String template, HashMap<String,Object> map) {
        //Context ctx = new Context();
        Context ctx = this.getContextWithGlobalVariables();
        try {
            for(Map.Entry<String, Object> entry : map.entrySet()) {
                ctx.setVariable(entry.getKey(), entry.getValue());
            }

            return engine.process(template, ctx);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Context getContextWithGlobalVariables() {
        Context ctx = new Context();
        ctx.setVariable("menu", this.menu);

        return ctx;
    }
}
