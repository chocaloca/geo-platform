/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2013 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 * This program is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version. This program is distributed in the 
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without 
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR 
 * A PARTICULAR PURPOSE. See the GNU General Public License 
 * for more details. You should have received a copy of the GNU General 
 * Public License along with this program. If not, see http://www.gnu.org/licenses/ 
 *
 * ====================================================================
 *
 * Linking this library statically or dynamically with other modules is 
 * making a combined work based on this library. Thus, the terms and 
 * conditions of the GNU General Public License cover the whole combination. 
 * 
 * As a special exception, the copyright holders of this library give you permission 
 * to link this library with independent modules to produce an executable, regardless 
 * of the license terms of these independent modules, and to copy and distribute 
 * the resulting executable under terms of your choice, provided that you also meet, 
 * for each linked independent module, the terms and conditions of the license of 
 * that module. An independent module is a module which is not derived from or 
 * based on this library. If you modify this library, you may extend this exception 
 * to your version of the library, but you are not obligated to do so. If you do not 
 * wish to do so, delete this exception statement from your version. 
 *
 */
package org.geosdi.geoplatform.gui.client.widget.wfs.map.dispatcher;

import javax.inject.Inject;
import org.geosdi.geoplatform.gui.client.command.wfst.feature.UpdateFeatureGeometryResponse;
import org.geosdi.geoplatform.gui.client.config.annotation.StatusBarFailedEvent;
import org.geosdi.geoplatform.gui.client.config.annotation.StatusBarLoadingEvent;
import org.geosdi.geoplatform.gui.client.config.annotation.StatusBarNotOkEvent;
import org.geosdi.geoplatform.gui.client.config.annotation.StatusBarSuccessEvent;
import org.geosdi.geoplatform.gui.client.puregwt.wfs.event.FeatureStatusBarEvent;
import org.geosdi.geoplatform.gui.command.api.ClientCommandDispatcher;
import org.geosdi.geoplatform.gui.command.api.GPClientCommand;
import org.gwtopenmaps.openlayers.client.feature.VectorFeature;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class WFSFeatureDispatcher extends AbstractFeatureDispatcher {

    @Inject
    public WFSFeatureDispatcher(
            @StatusBarLoadingEvent FeatureStatusBarEvent theLoadingEvent,
            @StatusBarSuccessEvent FeatureStatusBarEvent theSuccessEvent,
            @StatusBarNotOkEvent FeatureStatusBarEvent theStatusNotOk,
            @StatusBarFailedEvent FeatureStatusBarEvent theFailedEvent) {
        super(theLoadingEvent, theSuccessEvent, theStatusNotOk, theFailedEvent);

        super.addFeatureDispatcherHandler();
    }

    @Override
    public void updateGeometry(final VectorFeature modifiedFeature,
            String wktGeometry, final VectorFeature oldFeature) {
        progressBar.show();

        super.setUpRequest(modifiedFeature, wktGeometry);

        ClientCommandDispatcher.getInstance().execute(
                new GPClientCommand<UpdateFeatureGeometryResponse>() {

            private static final long serialVersionUID = 5836033208636357032L;

            {
                super.setCommandRequest(updateGeometryRequest);
            }

            @Override
            public void onCommandSuccess(UpdateFeatureGeometryResponse response) {
                manageCommandSuccess(response.getResult(), modifiedFeature,
                        oldFeature);
            }

            @Override
            public void onCommandFailure(Throwable exception) {
                manageCommandFailure(modifiedFeature, oldFeature, exception);
            }

        });
    }

}
