/**
 * geo-platform
 * Rich webgis framework
 * http://geo-platform.org
 * ====================================================================
 * <p>
 * Copyright (C) 2008-2018 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
 * wish to do so, operation this exception statement from your version.
 */
package org.geosdi.geoplatform.experimental.el.search.bool;

import org.elasticsearch.index.query.Operator;
import org.geosdi.geoplatform.experimental.el.search.IGPQuerySearch;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public interface IBooleanSearch extends IGPQuerySearch {

    /**
     * @return {@link BooleanQueryType}
     */
    BooleanQueryType getType();

    /**
     *
     */
    enum BooleanQueryType {
        MUST, SHOULD, MUST_NOT, FILTER;
    }

    /**
     *
     */
    enum OperatorType {
        AND, NOT;
    }

    /**
     *
     */
    abstract class AbstractBooleanSearch implements IBooleanSearch {
        protected final BooleanQueryType type;
        protected Object value;
        protected String field;
        protected Operator operator;

        /**
         * @param theField
         * @param theType
         */
        public AbstractBooleanSearch(String theField, BooleanQueryType theType) {
            checkArgument(((theField != null) && !(theField.isEmpty())),
                    "The Parameter Field must not be null or an Empty String.");
            checkArgument((theType != null), "The Parameter Type must not be null.");
            this.field = theField;
            this.type = theType;
        }

        /**
         * @param theField
         * @param theValue
         * @param theType
         */
        public AbstractBooleanSearch(String theField, Object theValue, BooleanQueryType theType) {
            checkArgument(((theField != null) && !(theField.isEmpty())),
                    "The Parameter Field must not be null or an Empty String.");
            checkArgument(((theValue != null)), "The Parameter Value must not be null.");
            checkArgument((theType != null), "The Parameter Type must not be null.");
            this.field = theField;
            this.value = theValue;
            this.type = theType;
        }

        /**
         * @param theValue
         * @param theType
         * @param theOperator
         */
        public AbstractBooleanSearch(Object theValue, BooleanQueryType theType,
                                     Operator theOperator) {
            checkArgument(((theValue != null)), "The Parameter Value must not be null.");
            checkArgument((theType != null), "The Parameter Type must not be null.");
            checkArgument((theOperator != null), "The Parameter Operator must not be null.");
            this.value = theValue;
            this.type = theType;
            this.operator = theOperator;
        }

        /**
         * @param theValue
         * @param theField
         * @param theType
         * @param theOperator
         */
        public AbstractBooleanSearch(Object theValue, String theField, BooleanQueryType theType,
                                     Operator theOperator) {
            checkArgument(((theValue != null)), "The Parameter Value must not be null.");
            checkArgument((theType != null), "The Parameter Type must not be null.");
            checkArgument((theOperator != null), "The Parameter Operator must not be null.");
            checkArgument(((theField != null) && !(theField.isEmpty())),
                    "The Parameter Field must not be null or an Empty String.");
            this.value = theValue;
            this.field = theField;
            this.type = theType;
            this.operator = theOperator;
        }

        /**
         * @return {@link BooleanQueryType}
         */
        @Override
        public BooleanQueryType getType() {
            return this.type;
        }

        /**
         * @return {@link String}
         */
        public String getField() {
            return this.field;
        }

        /**
         * @return {@link Object}
         */
        public Object getValue() {
            return this.value;
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + " {" +
                    " field = '" + field + '\'' +
                    ", value = '" + value + '\'' +
                    ", type = " + type +
                    ", operator = " + operator +
                    '}';
        }
    }
}
