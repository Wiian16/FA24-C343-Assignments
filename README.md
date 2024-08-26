This is the "assignments" repo for C343 that will contain the startercode for Assignments and Labs

## To Get Started
### Execute the following set-up steps in the terminal JUST ONCE.
1. Make sure that your github repo is named in the following format: username-submission. For example, if your username is jghafoor, your private github repo should be named jghafoor-submission
2. Open the terminal in your computer and type the following commands to clone this repository
```console
foo@bar:~$ cd IdeaProjects
foo@bar:IdeaProjects$ git init 
Initialized empty Git repository in /Users/foo/IdeaProjects/.git/
foo@bar:IdeaProjects$ git clone https://github.iu.edu/CSCI-C343-Fall2024/assignments.git
```
3. Check for existing remotes.
```console
foo@bar:IdeaProjects$ cd assignments
foo@bar:assignments$ git remote -v
origin	https://github.iu.edu/CSCI-C343-Fall2024/assignments.git (fetch)
origin	https://github.iu.edu/CSCI-C343-Fall2024/assignments.git (push)
```
4. Rename the remote origin to upstream. This remote upstream is the Teacher's "assignments" repo.
```console
foo@bar:assignments$ git remote rename origin upstream
foo@bar:assignments$ git remote -v
upstream	https://github.iu.edu/CSCI-C343-Fall2024/assignments.git (fetch)
upstream	https://github.iu.edu/CSCI-C343-Fall2024/assignments.git (push)
```
5. Add your "username-submission" repo as remote origin. You must replace "username" with you own IU username
```console
foo@bar:assignments$ git remote add origin https://github.iu.edu/CSCI-C343-Fall2024/username-submission.git
foo@bar:assignments$ git remote -v
origin	https://github.iu.edu/CSCI-C343-Fall2024/username-submission.git (fetch)
origin	https://github.iu.edu/CSCI-C343-Fall2024/username-submission.git (push)
upstream	https://github.iu.edu/CSCI-C343-Fall2024/assignments.git (fetch)
upstream	https://github.iu.edu/CSCI-C343-Fall2024/assignments.git (push)
```

## To Make Submissions:
The [Autograder page](https://autograder.luddy.indiana.edu) submission is must but not the sole reference for grading. Your assignments would be manually graded, by referring to your username-submission repo. So make sure to update your repo for each Assignment/Lab in accordance to their deadlines. Any commits post-deadline would not be considered.
To do this:
1. First of all, make sure that the latest changes from the Teacher's repo "assignments" have been Pulled
```console
foo@bar:assignments$ git fetch upstream      # download latest from teacher repo
foo@bar:assignments$ git checkout main       # ensure you are on your main branch
foo@bar:assignments$ git merge upstream/main # add your changes on top of those from upstream
```
2. Commit your code to GitHub (replace <...> in the following commands with the proper arguments)
```console
foo@bar:assignments$ git add <LIST OF FILES/FOLDERS YOU WANT TO COMMIT SEPARATED BY SPACE>
foo@bar:assignments$ git commit -m "<MESSAGE IN DOUBLE QUOTES>"
foo@bar:assignments$ git push origin main
```
3. Submit your FULL COMMIT URL to Canvas. The commit url would look something like this:
```console
https://github.iu.edu/CSCI-C343-Fall2024/username-submission/commit/26bc3f1a824151502adf56d061aaf5352d0216b2
```

## FAQ:
### Q1) How to fix the following error:
```console
To https://github.iu.edu/CSCI-C343-Fall2024/username-submission.git
 ! [rejected]        main -> main (fetch first)
error: failed to push some refs to 'https://github.iu.edu/CSCI-C343-Fall2024/username-submission.git'
hint: Updates were rejected because the remote contains work that you do
hint: not have locally. This is usually caused by another repository pushing
hint: to the same ref. You may want to first integrate the remote changes
hint: (e.g., 'git pull ...') before pushing again.
hint: See the 'Note about fast-forwards' in 'git push --help' for details.
```
### A1)
This error occurs when your username-submission repo is some commits ahead of what's on your local machine. To fix this error:
1. NECESSARY STEP: Make a copy of your "assignments" folder as assignments_copy. This is a necessary step as otherwise you would lose all of your work.
2. Run the following command:
```console
foo@bar:assignments$ git pull --rebase origin main
```
3. Copy all of the content from the assignments_copy folder back to "assignments" folder.
4. Run the following commands:
```console
foo@bar:assignments$ git add .
foo@bar:assignments$ git commit -m "<MESSAGE IN DOUBLE QUOTES>"
foo@bar:assignments$ git push origin main
```
If you still face any error in this process, please don't hesitate to ask on Discord.

### Q2) I tried fixing the following error using the method above, but it didn't work. What do I do?
```console
To https://github.iu.edu/CSCI-C343-Fall2024/username-submission.git
 ! [rejected]        main -> main (fetch first)
error: failed to push some refs to 'https://github.iu.edu/CSCI-C343-Fall2024/username-submission.git'
hint: Updates were rejected because the remote contains work that you do
hint: not have locally. This is usually caused by another repository pushing
hint: to the same ref. You may want to first integrate the remote changes
hint: (e.g., 'git pull ...') before pushing again.
hint: See the 'Note about fast-forwards' in 'git push --help' for details.
```

### A2)
1. Run the following commands:
```console
foo@bar:assignments$ git status
foo@bar:assignments$ git add <LIST OF ALL THE FILES YOU SEE IN RED ON RUNNING GIT STATUS SEPARATED BY SPACE>
foo@bar:assignments$ git commit -m "resolving conflicts"
foo@bar:assignments$ git push origin main
```
2. Then follow the FAQ Q1.
3. And then you shall be able to add, commit, and push without any errors.

If you still face any error in this process, please don't hesitate to ask on Discord.
