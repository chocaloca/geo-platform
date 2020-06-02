/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2020 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 *   This program is free software: you can redistribute it and/or modify it
 *   under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version. This program is distributed in the
 *   hope that it will be useful, but WITHOUT ANY WARRANTY; without
 *   even the implied warranty of MERCHANTABILITY or FITNESS FOR
 *   A PARTICULAR PURPOSE. See the GNU General Public License
 *   for more details. You should have received a copy of the GNU General
 *   Public License along with this program. If not, see http://www.gnu.org/licenses/
 *
 *   ====================================================================
 *
 *   Linking this library statically or dynamically with other modules is
 *   making a combined work based on this library. Thus, the terms and
 *   conditions of the GNU General Public License cover the whole combination.
 *
 *   As a special exception, the copyright holders of this library give you permission
 *   to link this library with independent modules to produce an executable, regardless
 *   of the license terms of these independent modules, and to copy and distribute
 *   the resulting executable under terms of your choice, provided that you also meet,
 *   for each linked independent module, the terms and conditions of the license of
 *   that module. An independent module is a module which is not derived from or
 *   based on this library. If you modify this library, you may extend this exception
 *   to your version of the library, but you are not obligated to do so. If you do not
 *   wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.gui.client.config;

import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.user.client.ui.ToggleButton;
import org.geosdi.geoplatform.gui.client.config.annotation.*;
import org.geosdi.geoplatform.gui.client.config.annotation.buttonbar.WFSGotoXYButton;
import org.geosdi.geoplatform.gui.client.config.annotation.buttonbar.WFSRedoButton;
import org.geosdi.geoplatform.gui.client.config.annotation.buttonbar.WFSUndoButton;
import org.geosdi.geoplatform.gui.client.config.provider.button.*;
import org.geosdi.geoplatform.gui.client.config.provider.togglebutton.*;
import org.geosdi.geoplatform.gui.client.widget.wfs.buttonbar.undoredo.mediator.BaseUndoRedoMediator;
import org.geosdi.geoplatform.gui.client.widget.wfs.buttonbar.undoredo.mediator.WFSUndoRedoMediator;

import javax.inject.Singleton;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class FeatureInjectorButtonProvider extends AbstractGinModule {

    @Override
    protected void configure() {
        bind(Button.class).annotatedWith(ResetButton.class).toProvider(ResetButtonProvider.class);
        bind(Button.class).annotatedWith(SaveButton.class).toProvider(SaveButtonProvider.class);
        bind(ToggleButton.class).annotatedWith(DragFeatureToggleButton.class).toProvider(DragFeatureToggleButtonProvider.class).in(Singleton.class);
        bind(ToggleButton.class).annotatedWith(EditFeatureToggleButton.class).toProvider(EditFeatureToggleButtonProvider.class).in(Singleton.class);
        bind(ToggleButton.class).annotatedWith(GetFeatureToggleButton.class).toProvider(GetFeatureToggleButtonProvider.class).in(Singleton.class);
        bind(ToggleButton.class).annotatedWith(ReshapeFeatureToggleButton.class).toProvider(ReshapeFeatureToggleButtonProvider.class).in(Singleton.class);
        bind(ToggleButton.class).annotatedWith(ResizeFeatureToggleButton.class).toProvider(ResizeFeatureToggleButtonProvider.class).in(Singleton.class);
        bind(ToggleButton.class).annotatedWith(RotateFeatureToggleButton.class).toProvider(RotateFeatureToggleButtonProvider.class).in(Singleton.class);
        bind(ToggleButton.class).annotatedWith(EraseFeatureToggleButton.class).toProvider(EraseFeatureToggleButtonProvider.class).in(Singleton.class);
        bind(Button.class).annotatedWith(WFSGotoXYButton.class).toProvider(WFSGotoXYButtonProvider.class).in(Singleton.class);
        bind(WFSUndoRedoMediator.class).to(BaseUndoRedoMediator.class).in(Singleton.class);
        bind(Button.class).annotatedWith(WFSUndoButton.class).toProvider(WFSUndoButtonProvider.class).in(Singleton.class);
        bind(Button.class).annotatedWith(WFSRedoButton.class).toProvider(WFSRedoButtonProvider.class).in(Singleton.class);
        bind(Button.class).annotatedWith(SelectFeaturesButton.class).toProvider(SelectFeatureButtonProvider.class).in(Singleton.class);
        bind(Button.class).annotatedWith(QueryFeatureButton.class).toProvider(QueryFeatureButtonProvider.class).in(Singleton.class);
        bind(Button.class).annotatedWith(GetAllFeaturesButton.class).toProvider(GetAllFeaturesButtonProvider.class).in(Singleton.class);
    }
}