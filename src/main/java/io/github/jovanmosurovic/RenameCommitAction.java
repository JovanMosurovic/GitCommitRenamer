package io.github.jovanmosurovic;

import com.intellij.openapi.actionSystem.ActionUpdateThread;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import git4idea.commands.Git;
import git4idea.commands.GitCommand;
import git4idea.commands.GitCommandResult;
import git4idea.commands.GitLineHandler;
import git4idea.repo.GitRepository;
import git4idea.repo.GitRepositoryManager;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public class RenameCommitAction extends AnAction {

    @Override
    public @NotNull ActionUpdateThread getActionUpdateThread() {
        // Most UI-related actions should use EDT
        return ActionUpdateThread.EDT;
    }

    @Override
    public void update(@NotNull AnActionEvent e) {
        Project project = e.getProject();
        if (project == null) {
            e.getPresentation().setEnabledAndVisible(false);
            return;
        }

        GitRepositoryManager manager = GitRepositoryManager.getInstance(project);
        // Check if there are any Git repositories by getting the collection
        boolean hasGitRepos = !manager.getRepositories().isEmpty();
        e.getPresentation().setEnabledAndVisible(hasGitRepos);
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Project project = e.getProject();
        if (project == null) {
            return;
        }

        GitRepositoryManager repositoryManager = GitRepositoryManager.getInstance(project);

        Collection<GitRepository> repositories = repositoryManager.getRepositories();
        if (repositories.isEmpty()) {
            Messages.showErrorDialog(project, "No Git repositories found", "Rename Commit");
            return;
        }

        // Get the current repository - use the first one available
        GitRepository repository = repositories.iterator().next();

        // Ask for the new commit message
        String newMessage = Messages.showInputDialog(
                project,
                "Enter new commit message:",
                "Rename Current Commit",
                Messages.getQuestionIcon()
        );

        if (newMessage == null || newMessage.trim().isEmpty()) {
            return; // User cancelled or entered empty message
        }

        // Run git commit --amend -m "new message"
        GitLineHandler handler = new GitLineHandler(project, repository.getRoot(), GitCommand.COMMIT);
        handler.addParameters("--amend", "-m", newMessage);

        GitCommandResult result = Git.getInstance().runCommand(handler);

        if (result.success()) {
            Messages.showInfoMessage(project, "Commit message changed successfully", "Success");
        } else {
            Messages.showErrorDialog(
                    project,
                    "Failed to rename commit: " + String.join("\n", result.getErrorOutput()),
                    "Error"
            );
        }
    }
}