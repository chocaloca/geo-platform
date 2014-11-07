/**
 *
 * geo-platform Rich webgis framework http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2014 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version. This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details. You should have received a copy of the GNU General Public License
 * along with this program. If not, see http://www.gnu.org/licenses/
 *
 * ====================================================================
 *
 * Linking this library statically or dynamically with other modules is making a
 * combined work based on this library. Thus, the terms and conditions of the
 * GNU General Public License cover the whole combination.
 *
 * As a special exception, the copyright holders of this library give you
 * permission to link this library with independent modules to produce an
 * executable, regardless of the license terms of these independent modules, and
 * to copy and distribute the resulting executable under terms of your choice,
 * provided that you also meet, for each linked independent module, the terms
 * and conditions of the license of that module. An independent module is a
 * module which is not derived from or based on this library. If you modify this
 * library, you may extend this exception to your version of the library, but
 * you are not obligated to do so. If you do not wish to do so, delete this
 * exception statement from your version.
 */
package org.geosdi.geoplatform.gui.client.widget.form.projects;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.event.WindowEvent;
import com.extjs.gxt.ui.client.event.WindowListener;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.CheckBoxGroup;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.HasRpcToken;
import com.google.gwt.user.client.rpc.RpcTokenException;
import com.google.gwt.user.client.rpc.XsrfToken;
import com.google.gwt.user.client.rpc.XsrfTokenServiceAsync;
import java.util.List;
import org.geosdi.geoplatform.gui.action.button.GPSecureButton;
import org.geosdi.geoplatform.gui.client.BasicWidgetResources;
import org.geosdi.geoplatform.gui.client.LayerResources;
import org.geosdi.geoplatform.gui.client.action.projects.AddProjectAction;
import org.geosdi.geoplatform.gui.client.i18n.LayerModuleConstants;
import org.geosdi.geoplatform.gui.client.i18n.buttons.ButtonsConstants;
import org.geosdi.geoplatform.gui.client.model.projects.GPClientProject;
import org.geosdi.geoplatform.gui.client.model.projects.GPClientProjectKey;
import org.geosdi.geoplatform.gui.client.service.LayerRemote;
import org.geosdi.geoplatform.gui.client.service.LayerRemoteAsync;
import org.geosdi.geoplatform.gui.client.widget.form.binding.GPDynamicFormBinding;
import org.geosdi.geoplatform.gui.client.widget.form.projects.binding.ProjectDefaultFieldBinding;
import org.geosdi.geoplatform.gui.client.widget.form.projects.binding.ProjectNameFieldBinding;
import org.geosdi.geoplatform.gui.client.widget.grid.pagination.listview.GPListViewSearchPanel;
import org.geosdi.geoplatform.gui.client.widget.pagination.projects.GPProjectSearchPanel;
import org.geosdi.geoplatform.gui.configuration.GPSecureStringTextField;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.puregwt.session.TimeoutHandlerManager;
import org.geosdi.geoplatform.gui.service.gwt.xsrf.GPXsrfTokenService;
import org.geosdi.geoplatform.gui.shared.GPTrustedLevel;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class ProjectBindingWidget extends GPDynamicFormBinding<GPClientProject> {

    private static final XsrfTokenServiceAsync xsrf = GPXsrfTokenService.Util.getInstance();
    private static final LayerRemoteAsync layerRemote = LayerRemote.Util.getInstance();
    //
    private final GPListViewSearchPanel<GPClientProject> searchWidget;
    private GPSecureStringTextField projectFieldName;
    private CheckBox projectDefaultCheck;
    private GPSecureButton save;
    private Button cancel;
    private FormButtonBinding buttonBinding;

    public ProjectBindingWidget(GPListViewSearchPanel<GPClientProject> theWidget) {
        super();
        this.searchWidget = theWidget;
        super.addWindowListener(new WindowListener() {

            @Override
            public void windowShow(WindowEvent we) {
                manageComponents();
            }
        });
    }

    @Override
    public void addFieldsBinding() {
        this.formBinding.setStore(this.searchWidget.getStore());
        super.formBinding.addFieldBinding(new ProjectNameFieldBinding(
                projectFieldName,
                GPClientProjectKey.PROJECT_NAME.toString()));
        super.formBinding.addFieldBinding(new ProjectDefaultFieldBinding(
                projectDefaultCheck,
                GPClientProjectKey.DEFAULT_PROJECT.toString()));
    }

    @Override
    public void addComponentToForm() {
        fieldSet = new FieldSet();
        fieldSet.setHeadingHtml(
                LayerModuleConstants.INSTANCE.ProjectBindingWidget_fieldSetHeadingText());
        FormLayout layout = new FormLayout();
        layout.setLabelWidth(120);
        layout.setLabelPad(5);
        fieldSet.setLayout(layout);
        this.projectFieldName = new GPSecureStringTextField();
        this.projectFieldName.setAllowBlank(false);
        this.projectFieldName.setEmptyText(
                LayerModuleConstants.INSTANCE.ProjectBindingWidget_projectFieldNameEmptyText());
        this.projectFieldName.setName(GPClientProjectKey.PROJECT_NAME.name());
        this.projectFieldName.setFieldLabel(
                LayerModuleConstants.INSTANCE.ProjectBindingWidget_projectFieldNameLabelText());
        fieldSet.add(this.projectFieldName);
        this.projectDefaultCheck = new CheckBox();
        this.projectDefaultCheck.setBoxLabel(
                LayerModuleConstants.INSTANCE.ProjectBindingWidget_projectDefaultCheckLabelText());
        this.projectDefaultCheck.setName(
                GPClientProjectKey.DEFAULT_PROJECT.toString());
        CheckBoxGroup checkGroup = new CheckBoxGroup();
        checkGroup.setFieldLabel(
                LayerModuleConstants.INSTANCE.ProjectBindingWidget_checkBoxGroupLabelText());
        checkGroup.add(this.projectDefaultCheck);
        fieldSet.add(checkGroup);
        this.getFormPanel().add(fieldSet);
        this.addButtons();
    }

    @Override
    public void initSize() {
        super.setHeadingHtml(
                LayerModuleConstants.INSTANCE.ProjectBindingWidget_headingText());
        setSize(420, 200);
    }

    @Override
    public void initSizeFormPanel() {
        super.getFormPanel().setHeaderVisible(Boolean.FALSE);
        super.getFormPanel().setSize(420, 200);
    }

    @Override
    public void execute() {
        if (this.entity.getId() == null) {
            insertProject();
        } else {
            updateProject();
        }
    }

    /**
     *
     * @param boolean useNewEntity TRUE to use a new Instance of GPClientProject
     * FALSE to use selected item in the store
     */
    public void showForm(boolean useNewEntity) {
        if (useNewEntity) {
            this.entity = new GPClientProject();
            this.entity.setNumberOfElements(0);
            this.entity.setImage(LayerResources.ICONS.gpProject().getHTML());
        } else {
            this.entity = this.searchWidget.getSelectionModel().getSelectedItem();
        }
        super.showForm();
        buttonBinding.startMonitoring();
    }

    /**
     * Important method to ensure the proper functioning of the GXT Model
     * binding
     */
    public void storeRejectChanges() {
        this.searchWidget.getStore().rejectChanges();
    }

    @Override
    public void reset() {
        storeRejectChanges();
        this.formBinding.unbind();
        this.projectFieldName.reset();
        this.projectDefaultCheck.reset();
    }

    /**
     * Important to enable / disable CheckBox for Default Project
     */
    private void manageComponents() {
        this.projectDefaultCheck.setEnabled(!this.entity.isDefaultProject());
    }

    private void addButtons() {
        getFormPanel().setButtonAlign(HorizontalAlignment.RIGHT);
        this.save = new GPSecureButton(ButtonsConstants.INSTANCE.saveText(),
                BasicWidgetResources.ICONS.save(),
                new AddProjectAction(GPTrustedLevel.HIGH, this));
        getFormPanel().addButton(save);
        buttonBinding = new FormButtonBinding(getFormPanel());
        buttonBinding.addButton(save);
        this.cancel = new Button(ButtonsConstants.INSTANCE.cancelText(),
                BasicWidgetResources.ICONS.cancel(),
                new SelectionListener<ButtonEvent>() {

                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        storeRejectChanges();
                        hide();
                    }
                });

        getFormPanel().addButton(cancel);
    }

    private void insertProject() {
        xsrf.getNewXsrfToken(new AsyncCallback<XsrfToken>() {

            @Override
            public void onFailure(Throwable caught) {
                try {
                    throw caught;
                } catch (RpcTokenException e) {
                    // Can be thrown for several reasons:
                    //   - duplicate session cookie, which may be a sign of a cookie
                    //     overwrite attack
                    //   - XSRF token cannot be generated because session cookie isn't
                    //     present
                } catch (Throwable e) {
                    // unexpected
                }
            }

            @Override
            public void onSuccess(XsrfToken token) {
                ((HasRpcToken) layerRemote).setRpcToken(token);
                layerRemote.saveProject(entity, new AsyncCallback<Long>() {

                    @Override
                    public void onFailure(Throwable caught) {
                        GeoPlatformMessage.errorMessage(
                                LayerModuleConstants.INSTANCE.ProjectBindingWidget_addProjectErrorText(),
                                caught.getMessage());
                    }

                    @Override
                    public void onSuccess(Long result) {
                        entity.setId(result);
                        searchWidget.getStore().insert(entity, 0);
                        if (entity.isDefaultProject()) {
                            changeDefaultProject();
                        }
                        searchWidget.getStore().commitChanges();

                        GeoPlatformMessage.infoMessage(
                                LayerModuleConstants.INSTANCE.ProjectBindingWidget_addProjectSuccessText(),
                                "<ul><li>" + entity.getName() + "</li></ul>");
                        if (entity.isDefaultProject()) {
                            TimeoutHandlerManager.fireEvent(
                                    ((GPProjectSearchPanel) searchWidget).getDefaultProjectEvent());

                        }
                        hide();
                    }
                });
            }
        });
    }

    private void updateProject() {
        xsrf.getNewXsrfToken(new AsyncCallback<XsrfToken>() {

            @Override
            public void onFailure(Throwable caught) {
                try {
                    throw caught;
                } catch (RpcTokenException e) {
                    // Can be thrown for several reasons:
                    //   - duplicate session cookie, which may be a sign of a cookie
                    //     overwrite attack
                    //   - XSRF token cannot be generated because session cookie isn't
                    //     present
                } catch (Throwable e) {
                    // unexpected
                }
            }

            @Override
            public void onSuccess(XsrfToken token) {
                ((HasRpcToken) layerRemote).setRpcToken(token);
                layerRemote.updateProject(entity, new AsyncCallback<Object>() {

                    @Override
                    public void onFailure(Throwable caught) {
                        GeoPlatformMessage.errorMessage(
                                LayerModuleConstants.INSTANCE.
                                ProjectBindingWidget_updateProjectErrorText(),
                                caught.getMessage());
                    }

                    @Override
                    public void onSuccess(Object result) {
                        searchWidget.getStore().remove(entity);
                        searchWidget.getStore().insert(entity, 0);
                        if (entity.isDefaultProject()) {
                            changeDefaultProject();
                        }
                        searchWidget.getStore().commitChanges();
                        GeoPlatformMessage.infoMessage(
                                LayerModuleConstants.INSTANCE.
                                ProjectBindingWidget_updateProjectSuccessText(),
                                "<ul><li>" + entity.getName() + "</li></ul>");
                        if (entity.isDefaultProject()) {
                            TimeoutHandlerManager.fireEvent(
                                    ((GPProjectSearchPanel) searchWidget).getDefaultProjectEvent());

                        }

                        hide();
                    }
                });
            }
        });
    }

    private void changeDefaultProject() {
        List<GPClientProject> projects = this.searchWidget.getStore().getModels();
        for (int i = 1; i < projects.size(); i++) {
            GPClientProject gPClientProject = projects.get(i);
            if (gPClientProject.isDefaultProject()) {
                gPClientProject.setDefaultProject(Boolean.FALSE);
//                gPClientProject.setLabelDefaultProject(Boolean.FALSE);
                return;
            }
        }
    }
}
