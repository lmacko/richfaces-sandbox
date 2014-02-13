package org.richfaces.bootstrap.ui.paginationitem;

import org.richfaces.renderkit.RendererBase;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;

/**
 * Base class for the paginationItem renderer
 *
 * @author Lukas Eichler
 */
@ResourceDependencies({
        @ResourceDependency(library = "javax.faces", name = "jsf.js"), @ResourceDependency(library = "org.richfaces", name = "jquery.js"), @ResourceDependency(library = "org.richfaces", name = "richfaces.js"), @ResourceDependency(library = "org.richfaces", name = "richfaces-queue.reslib"),
        @ResourceDependency(library = "org.richfaces", name = "common/richfaces-base-component.js"),
        @ResourceDependency(library = "org.richfaces", name = "bootstrap-css.reslib")})
public abstract class PaginationItemRendererBase extends RendererBase {
    public static final String RENDERER_TYPE = "org.richfaces.bootstrap.PaginationItemRenderer";

}
