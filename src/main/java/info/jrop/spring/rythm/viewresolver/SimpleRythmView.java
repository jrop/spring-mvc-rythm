package info.jrop.spring.rythm.viewresolver;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rythmengine.RythmEngine;
import org.springframework.web.servlet.View;

public class SimpleRythmView implements View {
	private SimpleRythmViewResolver ctx;
	private String name;

	public SimpleRythmView(SimpleRythmViewResolver simpleRythmViewResolver, String name) {
		this.ctx = simpleRythmViewResolver;
		this.name = name;
	}

	@Override
	public String getContentType() {
		return "text/html";
	}

	@Override
	public void render(Map<String, ?> model, HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String root = req.getSession().getServletContext().getRealPath(ctx.getRootDirectory()) + "/";
		File f = new File(root + name + ctx.getSuffix());
		
		resp.setContentType("text/html");
		RythmEngine eng = RythmEngineFactory.getEngine(req, ctx.getRootDirectory());
		String s = eng.render(f, model);
		resp.getWriter().write(s);
	}
}
