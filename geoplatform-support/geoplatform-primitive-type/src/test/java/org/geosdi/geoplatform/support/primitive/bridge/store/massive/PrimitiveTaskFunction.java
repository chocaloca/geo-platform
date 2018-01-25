/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2018 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.support.primitive.bridge.store.massive;

import com.google.common.base.Preconditions;

import java.util.concurrent.Callable;
import java.util.function.Function;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class PrimitiveTaskFunction implements Function<Integer, Callable<Long>> {

    private final PrimitiveTaskType primitiveTaskType;

    public PrimitiveTaskFunction(PrimitiveTaskType thePrimitiveTaskType) {
        Preconditions.checkArgument(thePrimitiveTaskType != null, "The Parameter Primitive Task Type must " +
                "not be null.");
        this.primitiveTaskType = thePrimitiveTaskType;
    }

    /**
     * Applies this function to the given argument.
     *
     * @param integer the function argument
     * @return the function result
     */
    @Override
    public Callable<Long> apply(Integer integer) {
        switch (this.primitiveTaskType) {
            case INTEGER:
                return new AbstractMassiveImplementorStoreTest.IntegerPrimitiveTask();
            case BIGDECIMAL:
                return new AbstractMassiveImplementorStoreTest.BigDecimalPrimitiveTask();
            case BIGINTEGER:
                return new AbstractMassiveImplementorStoreTest.BigIntegerPrimitiveTask();
            case BOOLEAN:
                return new AbstractMassiveImplementorStoreTest.BooleanPrimitiveTask();
            case BYTE:
                return new AbstractMassiveImplementorStoreTest.BytePrimitiveTask();
            case DOUBLE:
                return new AbstractMassiveImplementorStoreTest.DoublePrimitiveTask();
            case FLOAT:
                return new AbstractMassiveImplementorStoreTest.FloatPrimitiveTask();
            case LONG:
                return new AbstractMassiveImplementorStoreTest.LongPrimitiveTask();
            case NUMBER:
                return new AbstractMassiveImplementorStoreTest.NumberPrimitiveTask();
            case SHORT:
                return new AbstractMassiveImplementorStoreTest.ShortPrimitiveTask();
            case CHARACTER:
                return new AbstractMassiveImplementorStoreTest.CharacterPrimitiveTask();
            case STRING:
                return new AbstractMassiveImplementorStoreTest.StringPrimitiveTask();
            case ALL_IMPLEMENTORS:
                return new AbstractMassiveImplementorStoreTest.AllImplementorsTask();
            default:
                return new AbstractMassiveImplementorStoreTest.IntegerPrimitiveTask();
        }
    }
}
