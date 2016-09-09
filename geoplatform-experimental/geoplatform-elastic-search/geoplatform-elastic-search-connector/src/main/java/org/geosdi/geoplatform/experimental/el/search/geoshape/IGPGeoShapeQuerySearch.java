package org.geosdi.geoplatform.experimental.el.search.geoshape;

import com.google.common.base.Preconditions;
import jdk.nashorn.internal.ir.annotations.Immutable;
import org.elasticsearch.common.geo.ShapeRelation;
import org.elasticsearch.common.geo.builders.ShapeBuilder;
import org.elasticsearch.index.query.GeoShapeQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.geosdi.geoplatform.experimental.el.search.bool.IBooleanSearch;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPGeoShapeQuerySearch extends IBooleanSearch {

    /**
     * @return {@link GPShapeRelation}
     */
    GPShapeRelation getShapeRelation();

    /**
     * @return {@link ShapeBuilder}
     */
    ShapeBuilder getShapeBuilder();

    /**
     *
     */
    enum GPShapeRelation {
        INTERSECTS, DISJOINT, WITHIN, CONTAINS;
    }

    /**
     *
     */
    @Immutable
    class GPGeoShapeQuerySearch extends IBooleanSearch.AbstractBooleanSearch implements IGPGeoShapeQuerySearch {

        private final GPShapeRelation shapeRelation;
        private final ShapeBuilder shapeBuilder;

        public GPGeoShapeQuerySearch(String theField, BooleanQueryType theType, GPShapeRelation theShapeRelation,
                ShapeBuilder theShapeBuilder) {
            super(theField, theType);
            Preconditions.checkArgument((theShapeRelation != null), "The Parameter ShapeRelations must not be null.");
            Preconditions.checkArgument((theShapeBuilder != null), "The Parameter ShapeBuilder must not be null.");
            this.shapeRelation = theShapeRelation;
            this.shapeBuilder = theShapeBuilder;
        }

        /**
         * @return {@link GPShapeRelation}
         */
        @Override
        public GPShapeRelation getShapeRelation() {
            return this.shapeRelation;
        }

        /**
         * @return {@link ShapeBuilder}
         */
        @Override
        public ShapeBuilder getShapeBuilder() {
            return this.shapeBuilder;
        }

        /**
         * @return {@link QueryBuilder}
         */
        @Override
        public QueryBuilder buildQuery() {
            return this.buildGeoShapeQueryBuilder();
        }

        /**
         * @return {@link GeoShapeQueryBuilder}
         */
        protected final GeoShapeQueryBuilder buildGeoShapeQueryBuilder() {
            switch (this.shapeRelation) {
                case INTERSECTS:
                    return QueryBuilders.geoIntersectionQuery(this.field, this.shapeBuilder);
                case DISJOINT:
                    return QueryBuilders.geoDisjointQuery(this.field, this.shapeBuilder);
                case WITHIN:
                    return QueryBuilders.geoWithinQuery(this.field, this.shapeBuilder);
                case CONTAINS:
                    return QueryBuilders.geoShapeQuery(this.field, getShapeBuilder(), ShapeRelation.CONTAINS);
            }
            return null;
        }
    }
}
