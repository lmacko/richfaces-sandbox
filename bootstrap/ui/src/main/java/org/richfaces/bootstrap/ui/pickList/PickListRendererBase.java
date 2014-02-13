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
package org.richfaces.bootstrap.ui.pickList;

import java.util.Iterator;
import java.util.List;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.UIColumn;

import org.richfaces.ui.select.AbstractSelectManyComponent;
import org.richfaces.ui.select.ClientSelectItem;
import org.richfaces.ui.select.SelectManyHelper;
import org.richfaces.ui.select.SelectManyRendererBase;
import org.richfaces.util.HtmlUtil;

import com.google.common.collect.Iterators;

/**
 * Base class for the pickList renderer
 *
 * @author <a href="http://community.jboss.org/people/bleathem">Brian Leathem</a>
 * 
 */
@ResourceDependencies({
        @ResourceDependency(library = "javax.faces", name = "jsf.js"), @ResourceDependency(library = "org.richfaces", name = "jquery.js"), @ResourceDependency(library = "org.richfaces", name = "richfaces.js"), @ResourceDependency(library = "org.richfaces", name = "richfaces-queue.reslib"),
        @ResourceDependency(library = "org.richfaces", name = "common/richfaces-base-component.js"),
        @ResourceDependency(library = "org.richfaces", name = "bootstrap-css.reslib"),

        @ResourceDependency(library = "com.jqueryui", name = "js/jquery.ui.position.js"),
        @ResourceDependency(library = "com.jqueryui", name = "js/jquery.ui.core.js"),
        @ResourceDependency(library = "com.jqueryui", name = "js/jquery.ui.widget.js"),
        @ResourceDependency(library = "com.jqueryui", name = "js/jquery.ui.mouse.js"),
        @ResourceDependency(library = "com.jqueryui", name = "js/jquery.ui.button.js"),
        @ResourceDependency(library = "com.jqueryui", name = "js/jquery.ui.selectable.js"),
        @ResourceDependency(library = "com.jqueryui", name = "js/jquery.ui.sortable.js"),

        @ResourceDependency(library = "org.richfaces", name = "widget/pickList.js"),
        @ResourceDependency(library = "org.richfaces", name = "widget/orderingList.js"),
        @ResourceDependency(library = "org.richfaces", name = "bridge/bootstrap.js"),
        @ResourceDependency(library = "org.richfaces", name = "bridge/bridgeBase.js"),
        @ResourceDependency(library = "org.richfaces", name = "bridge/pickList.js")

        })
public abstract class PickListRendererBase extends SelectManyRendererBase {
    public static final String RENDERER_TYPE = "org.richfaces.bootstrap.PickListRenderer";

    protected String[] getColumnClasses(AbstractSelectManyComponent pickList) {
        String[] columnClasses;
        if (pickList.getColumnClasses() != null) {
            columnClasses = pickList.getColumnClasses().split(",");
        } else {
            columnClasses = new String[0];
        }
        return columnClasses;
    }

    protected String getColumnClass(UIColumn column, String[] columnClasses, int columnCount) {
        String columnClass = (String) column.getAttributes().get("styleClass");
        if (columnClasses != null && columnClasses.length > 0) {
            columnClass = HtmlUtil.concatClasses(columnClasses[columnCount % columnClasses.length], columnClass);
        }
        return columnClass;
    }

    protected Iterator<ClientSelectItem> getSourceSelectItems(List<ClientSelectItem> clientSelectItems) {
        return Iterators.filter(clientSelectItems.iterator(), SelectManyHelper.UNSELECTED_PREDICATE);
    }

    protected Iterator<ClientSelectItem> getTargetSelectItems(List<ClientSelectItem> clientSelectItems) {
        return Iterators.filter(clientSelectItems.iterator(), SelectManyHelper.SELECTED_PREDICATE);
    }
}
