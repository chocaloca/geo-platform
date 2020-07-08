/**
 * geo-platform
 * Rich webgis framework
 * http://geo-platform.org
 * ====================================================================
 * <p>
 * Copyright (C) 2008-2020 geoSDI Group (CNR IMAA - Potenza - ITALY).
 * <p>
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. This program is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details. You should have received a copy of the GNU General
 * Public License along with this program. If not, see http://www.gnu.org/licenses/
 * <p>
 * ====================================================================
 * <p>
 * Linking this library statically or dynamically with other modules is
 * making a combined work based on this library. Thus, the terms and
 * conditions of the GNU General Public License cover the whole combination.
 * <p>
 * As a special exception, the copyright holders of this library give you permission
 * to link this library with independent modules to produce an executable, regardless
 * of the license terms of these independent modules, and to copy and distribute
 * the resulting executable under terms of your choice, provided that you also meet,
 * for each linked independent module, the terms and conditions of the license of
 * that module. An independent module is a module which is not derived from or
 * based on this library. If you modify this library, you may extend this exception
 * to your version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.connector.server.request;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.FALSE;
import static java.util.Arrays.stream;
import static java.util.Optional.empty;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public enum WMSRequestKey implements GPWMSRequestKey {

    LAYERS("LAYERS"),
    SRS("SRS"),
    WIDTH("WIDTH"),
    HEIGHT("HEIGHT"),
    BBOX("BBOX"),
    CHAIN("&");

    private final String key;

    /**
     * @param theKey
     */
    WMSRequestKey(@Nonnull(when = NEVER) String theKey) {
        checkArgument((theKey != null) && !(theKey.trim().isEmpty()), "The Parameter key must not be null or an empty string.");
        this.key = theKey;
    }

    /**
     * @return {@link String}
     */
    @Override
    public String toKey() {
        return this.key;
    }

    /**
     * @param theKey
     * @return {@link GPWMSRequestKey}
     */
    public static GPWMSRequestKey forKey(@Nullable String theKey) {
        Optional<GPWMSRequestKey> optional = stream(WMSRequestKey.values())
                .map(v -> (GPWMSRequestKey) v)
                .filter(k -> ((theKey != null)) ? k.toKey().equalsIgnoreCase(theKey) : FALSE).findFirst();
        return ((optional != null) && !(optional.equals(empty()))) ? optional.get() : null;
    }
}