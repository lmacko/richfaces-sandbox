package org.richfaces.bootstrap.ui.breadcrumb;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;

import org.richfaces.renderkit.RendererBase;

/**
 * Base class for the breadcrumb renderer
 * 
 * @author damien.gouyette@gmail.com
 */
@ResourceDependencies({ @ResourceDependency(library = "javax.faces", name = "jsf.js"), @ResourceDependency(library = "org.richfaces", name = "jquery.js"), @ResourceDependency(library = "org.richfaces", name = "richfaces.js"), @ResourceDependency(library = "org.richfaces", name = "richfaces-queue.reslib"),
        @ResourceDependency(library = "org.richfaces", name = "common/richfaces-base-component.js"),
        @ResourceDependency(library = "org.richfaces", name = "bootstrap-css.reslib") })
public class BreadcrumbRendererBase extends RendererBase {
    public static final String RENDERER_TYPE = "org.richfaces.bootstrap.BreadcrumbRenderer";
}
