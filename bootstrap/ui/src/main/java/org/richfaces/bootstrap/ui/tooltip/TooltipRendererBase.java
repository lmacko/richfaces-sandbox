/**
 * JBoss, Home of Professional Open Source
 * Copyright , Red Hat, Inc. and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.richfaces.bootstrap.ui.tooltip;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.context.FacesContext;

import org.richfaces.javascript.JSLiteral;
import org.richfaces.javascript.JavaScriptService;
import org.richfaces.renderkit.RendererBase;
import org.richfaces.services.ServiceTracker;

/**
 * Base class for the tooltip renderer
 * 
 * @author Lukas Fryc
 */
@ResourceDependencies({
        @ResourceDependency(library = "javax.faces", name = "jsf.js"), @ResourceDependency(library = "org.richfaces", name = "jquery.js"), @ResourceDependency(library = "org.richfaces", name = "richfaces.js"), @ResourceDependency(library = "org.richfaces", name = "richfaces-queue.reslib"),
        @ResourceDependency(library = "org.richfaces", name = "common/richfaces-base-component.js"),
        @ResourceDependency(library = "org.richfaces", name = "bootstrap-css.reslib"),
        @ResourceDependency(library = "org.richfaces", name = "bootstrap-js.reslib")})
public abstract class TooltipRendererBase extends RendererBase {
    public static final String RENDERER_TYPE = "org.richfaces.bootstrap.TooltipRenderer";
    
    private static JSLiteral TOOLTIP_INIT_CODE = new JSLiteral("$('[rel=tooltip]').tooltip();");
    
    public void ensureInitCode(FacesContext facesContext) {
        JavaScriptService service = ServiceTracker.getService(JavaScriptService.class);
        
        service.addPageReadyScript(facesContext, TOOLTIP_INIT_CODE);
    }
}