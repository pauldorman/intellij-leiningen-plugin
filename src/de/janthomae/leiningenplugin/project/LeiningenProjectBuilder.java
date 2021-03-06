package de.janthomae.leiningenplugin.project;

import com.intellij.openapi.module.ModifiableModuleModel;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ui.configuration.ModulesProvider;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.packaging.artifacts.ModifiableArtifactModel;
import com.intellij.projectImport.ProjectImportBuilder;
import de.janthomae.leiningenplugin.LeiningenIcons;

import javax.swing.*;
import java.util.List;

/**
 * @author <a href="janthomae@janthomae.de">Jan Thom&auml;</a>
 * @version $Id:$
 */
public class LeiningenProjectBuilder extends ProjectImportBuilder<LeiningenProject> {
    private VirtualFile myProjectFile;

    @Override
    public String getName() {
        return "Leiningen";
    }

    @Override
    public Icon getIcon() {
        return LeiningenIcons.PROJECT_ICON;
    }

    @Override
    public List<LeiningenProject> getList() {
        return null;
    }

    @Override
    public boolean isMarked(LeiningenProject leiningenProject) {
        return true;
    }

    @Override
    public void setList(List<LeiningenProject> leiningenProjects) {
    }

    @Override
    public void setOpenProjectSettingsAfter(boolean b) {

    }

    @Override
    public List<Module> commit(Project project, ModifiableModuleModel modifiableModuleModel,
                               ModulesProvider modulesProvider, ModifiableArtifactModel modifiableArtifactModel) {

        LeiningenProjectsManager manager = LeiningenProjectsManager.getInstance(project);
        LeiningenProject leiningenProject = new LeiningenProject(myProjectFile, project);
        return manager.importLeiningenProject(leiningenProject);
    }

    public void setProjectFile(VirtualFile projectFile) {
        myProjectFile = projectFile;
    }

    public String getSuggestedProjectName() {
        return LeiningenProject.nameAndVersionFromProjectFile(myProjectFile)[0];
    }
}
