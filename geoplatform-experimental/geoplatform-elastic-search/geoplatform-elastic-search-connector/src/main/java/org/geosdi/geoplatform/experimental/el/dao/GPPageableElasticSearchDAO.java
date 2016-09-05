package org.geosdi.geoplatform.experimental.el.dao;

import net.jcip.annotations.Immutable;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AbstractAggregationBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.geosdi.geoplatform.experimental.el.api.model.Document;
import org.geosdi.geoplatform.experimental.el.search.bool.IBooleanSearch;
import org.geosdi.geoplatform.experimental.el.search.date.IGPDateQuerySearch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPPageableElasticSearchDAO<D extends Document> {

    /**
     * @param <P>
     * @param page
     * @return {@link IPageResult <D>}
     * @throws Exception
     */
    <P extends Page> IPageResult<D> find(P page) throws Exception;

    /**
     * @param page
     * @param includeFields
     * @param excludeFields
     * @param <P>
     * @return {@link IPageResult <D>}
     * @throws Exception
     */
    <P extends Page> IPageResult<D> find(P page, String[] includeFields, String[] excludeFields)
            throws Exception;

    /**
     * @param page
     * @param includeField
     * @param excludeField
     * @param <P>
     * @return {@link IPageResult <D>}
     * @throws Exception
     */
    <P extends Page> IPageResult<D> find(P page, String includeField, String excludeField)
            throws Exception;

    /**
     * @param page
     * @param aggregationBuilder
     * @param <P>
     * @return {@link SearchResponse}
     * @throws Exception
     */
    <P extends Page> SearchResponse find(P page, AbstractAggregationBuilder aggregationBuilder)
            throws Exception;

    /**
     * @param page
     * @param <P>
     * @return {@link Boolean}
     * @throws Exception
     */
    <P extends Page> Boolean deleteByPage(P page) throws Exception;

    /**
     * @param page
     * @param <P>
     * @return {@link CompletableFuture<Boolean>}
     * @throws Exception
     */
    <P extends Page> CompletableFuture<Boolean> deleteByPageAsync(P page) throws Exception;

    /**
     *
     */
    interface IPageResult<D> {

        /**
         * <p>The Total Results Number</p>
         *
         * @return {@link Long}
         */
        Long getTotal();

        /**
         * @return {@link List <D>}
         */
        List<D> getResults();

    }

    /**
     *
     */
    interface PageBuilder {

        /**
         * @param builder
         * @param <Builder>
         * @return {@link SearchRequestBuilder} Builder
         * @throws Exception
         */
        <Builder extends SearchRequestBuilder> Builder buildPage(Builder builder)
                throws Exception;
    }

    /**
     *
     */
    interface MultiFieldPageBuilder extends PageBuilder {

        /**
         * @return {@link BoolQueryBuilder}
         */
        BoolQueryBuilder boolQueryBuilder();

        /**
         * @param search
         * @param <Search>
         */
        default <Search extends IBooleanSearch> void buildQuery(Search search) {
            switch (search.getType()) {
                case SHOULD:
                    boolQueryBuilder().should(search.buildQuery());
                    break;
                case MUST:
                    boolQueryBuilder().must(search.buildQuery());
                    break;
                case MUST_NOT:
                    boolQueryBuilder().mustNot(search.buildQuery());
                    break;
            }
        }
    }

    /**
     *
     */
    @Immutable
    class Page implements PageBuilder {

        protected static final Logger logger = LoggerFactory.getLogger(Page.class);
        //
        private final int from;
        private final int size;

        public Page(int from, int size) {
            this.from = from;
            this.size = size;
        }

        /**
         * @return the from
         */
        public int getFrom() {
            return from;
        }

        /**
         * @return the size
         */
        public int getSize() {
            return size;
        }

        private Boolean canBuildPage() {
            return (this.size > 0);
        }

        private <Builder extends SearchRequestBuilder> Builder internalBuildPage(Builder builder)
                throws Exception {
            logger.trace("####################Called {} #internalBuildPage with parameters " +
                    "from : {} - size : {}\n\n", getClass().getSimpleName(), from, size);

            return (Builder) ((this.from >= 0) ? builder.setFrom(this.from).setSize(this.size) : builder.setSize(this.size));
        }

        @Override
        public <Builder extends SearchRequestBuilder> Builder buildPage(Builder builder)
                throws Exception {
            return (canBuildPage() ? this.internalBuildPage(builder) : builder);
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + " {"
                    + " from = " + from
                    + ", size = " + size + '}';
        }
    }

    /**
     *
     */
    @Immutable
    class SortablePage extends Page {

        private final String field;
        private final SortOrder sortOrder;

        /**
         * <p>In this case no Pagination , and Elastic Search will return only
         * 10 results in case of Match
         * </p>
         *
         * @param field
         * @param sortOrder
         */
        public SortablePage(String field, SortOrder sortOrder) {
            this(field, sortOrder, 0, 0);
        }

        public SortablePage(String field, SortOrder sortOrder, int from,
                int size) {
            super(from, size);
            this.field = field;
            this.sortOrder = sortOrder;
        }

        /**
         * @return the field
         */
        public String getField() {
            return field;
        }

        /**
         * @return the sortOrder
         */
        public SortOrder getSortOrder() {
            return sortOrder;
        }

        private Boolean canBuildPage() {
            return (((this.field != null) && !(this.field.isEmpty()))
                    && (this.sortOrder != null));
        }

        private <Builder extends SearchRequestBuilder> Builder internalBuildPage(Builder builder)
                throws Exception {
            logger.trace("####################Called {} #internalBuildPage with parameters " +
                    "field : {} - sortOrder : {}\n\n", getClass().getSimpleName(), field, sortOrder);

            return (Builder) builder.addSort(this.field, this.sortOrder);
        }

        @Override
        public <Builder extends SearchRequestBuilder> Builder buildPage(Builder builder) throws Exception {
            return (canBuildPage() ? internalBuildPage(super.buildPage(builder)) : super.buildPage(builder));
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + " {"
                    + " from = " + super.getFrom()
                    + ", size = " + super.getSize()
                    + ", field = " + field
                    + ", sortOrder = " + sortOrder + '}';
        }

    }

    /**
     *
     */
    @Immutable
    class QueriableSortablePage extends SortablePage {

        private final QueryBuilder query;

        public QueriableSortablePage(QueryBuilder query) {
            this(0, 0, query);
        }

        public QueriableSortablePage(String field, SortOrder sortOrder, QueryBuilder query) {
            this(field, sortOrder, 0, 0, query);
        }

        public QueriableSortablePage(int from, int size, QueryBuilder query) {
            this(null, null, from, size, query);
        }

        public QueriableSortablePage(String field, SortOrder sortOrder, int from,
                int size, QueryBuilder query) {
            super(field, sortOrder, from, size);
            this.query = query;
        }

        /**
         * @return the query to perform
         */
        public QueryBuilder getQuery() {
            return query;
        }

        private Boolean canBuildPage() {
            return (this.query != null);
        }

        private <Builder extends SearchRequestBuilder> Builder internalBuildPage(Builder builder)
                throws Exception {
            logger.trace("####################Called {} #internalBuildPage with parameters " +
                    "query : {}\n\n", getClass().getSimpleName(), query);


            return (Builder) builder.setQuery(this.query);
        }

        @Override
        public <Builder extends SearchRequestBuilder> Builder buildPage(Builder builder) throws Exception {
            return (canBuildPage() ? internalBuildPage(super.buildPage(builder)) : super.buildPage(builder));
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + " {"
                    + " from = " + super.getFrom()
                    + ", size = " + super.getSize()
                    + ", field = " + super.getField()
                    + ", sortOrder = " + super.getSortOrder()
                    + ", query = " + query + '}';
        }
    }

    /**
     *
     */
    @Immutable
    class DateRangeSortablePage extends SortablePage implements MultiFieldPageBuilder {

        private final IGPDateQuerySearch[] dateQuerySearch;
        private BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();

        public DateRangeSortablePage(IGPDateQuerySearch... theDateQuerySearch) {
            this(null, null, theDateQuerySearch);
        }

        public DateRangeSortablePage(int from, int size, IGPDateQuerySearch... theDateQuerySearch) {
            this(null, null, from, size, theDateQuerySearch);
        }

        public DateRangeSortablePage(String field, SortOrder sortOrder, IGPDateQuerySearch... theDateQuerySearch) {
            this(field, sortOrder, 0, 0, theDateQuerySearch);
        }

        public DateRangeSortablePage(String field, SortOrder sortOrder, int from, int size,
                IGPDateQuerySearch... theDateQuerySearch) {
            super(field, sortOrder, from, size);
            this.dateQuerySearch = theDateQuerySearch;
        }

        /**
         * @return {@link IGPDateQuerySearch}
         */
        public IGPDateQuerySearch[] getDateQuerySearch() {
            return this.dateQuerySearch;
        }

        /**
         * @return {@link BoolQueryBuilder}
         */
        @Override
        public BoolQueryBuilder boolQueryBuilder() {
            return this.queryBuilder;
        }

        private Boolean canBuildPage() {
            return ((this.dateQuerySearch != null) && (this.dateQuerySearch.length > 0));
        }

        private <Builder extends SearchRequestBuilder> Builder internalBuildPage(Builder builder)
                throws Exception {
            logger.trace("####################Called {} #internalBuildPage with parameters " +
                    "dateQuerySearch : {} \n\n", getClass().getSimpleName(), this.dateQuerySearch);
            Arrays.stream(this.dateQuerySearch)
                    .filter(q -> q != null)
                    .forEach(q -> buildQuery(q));
            builder.setQuery(queryBuilder);
            logger.trace("####################{} Query Created: \n{} \n\n", getClass().getSimpleName(), builder);
            return builder;
        }

        @Override
        public <Builder extends SearchRequestBuilder> Builder buildPage(Builder builder) throws Exception {
            return (canBuildPage() ? internalBuildPage(super.buildPage(builder)) : super.buildPage(builder));
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + " {" +
                    "  from = " + super.getFrom() +
                    ", size = " + super.getSize() +
                    ", field = " + super.getField() +
                    ", sortOrder = " + super.getSortOrder() +
                    " ,dateQuerySearch = '" + dateQuerySearch + '\'' +
                    '}';
        }
    }

    @Immutable
    class MultiFieldsSearch extends SortablePage implements MultiFieldPageBuilder {

        private final IBooleanSearch[] queryList;
        private BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();

        public MultiFieldsSearch(IBooleanSearch... queryList) {
            this(null, null, 0, 0, queryList);
        }

        public MultiFieldsSearch(int from, int size, IBooleanSearch... queryList) {
            this(null, null, from, size, queryList);
        }

        public MultiFieldsSearch(String field, SortOrder sortOrder, IBooleanSearch... queryList) {
            this(field, sortOrder, 0, 0, queryList);
        }

        public MultiFieldsSearch(String field, SortOrder sortOrder, int from,
                int size, IBooleanSearch... queryList) {
            super(field, sortOrder, from, size);
            this.queryList = queryList;
        }

        private Boolean canBuildPage() {
            return this.queryList != null && (this.queryList.length > 0);
        }

        private <Builder extends SearchRequestBuilder> Builder internalBuildPage(Builder builder)
                throws Exception {
            logger.trace("####################Called {} #internalBuildPage with parameters " +
                    "queryList : {} \n\n", getClass().getSimpleName(), this.queryList);
            Arrays.stream(this.queryList).forEach(q -> buildQuery(q));
            logger.trace("####################{} - Create Query: \n{} \n\n", getClass().getSimpleName(),
                    this.queryBuilder.toString());
            return (Builder) builder.setQuery(queryBuilder);
        }

        @Override
        public <Builder extends SearchRequestBuilder> Builder buildPage(Builder builder) throws Exception {
            return (canBuildPage() ? internalBuildPage(super.buildPage(builder)) : super.buildPage(builder));
        }

        /**
         * @return {@link BoolQueryBuilder}
         */
        @Override
        public BoolQueryBuilder boolQueryBuilder() {
            return this.queryBuilder;
        }
    }
}
