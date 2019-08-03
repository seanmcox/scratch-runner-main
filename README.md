# scratch-runner-main
The initializer for the Scratch Runner. Starts up Apache Felix and loads the scratch runtime implementation.

The compiled Scratch Runner can be downloaded from <a href="http://theshtick.org/scratchrunner/">http://theshtick.org/scratchrunner/</a>

This current version is being upgraded for Scratch3 and may not work with the old Scratch 2 emulator. (But this will all be resolved.)

This is an Eclipse project. This is also the main project for the Scratch Runner, which contains the tasks for building the installation packages. The intended build method requires that you have Eclipse installed with the Java Development Tools. There is a runner for Scratch2 and one for Scratch 3. The complete set of related projects involves 7 repositories:

* `scratch-runner-main`

Scratch 2 Repositories:
* `scratch-runner-core`
* `scratch-runner-implementation`
* `scratch-runner-blocks-standard`

Scratch 3 Repositories:
* `scratch3-runner-core`
* `scratch3-runner-implementation`
* `scratch3-runner-blocks-standard`

Once these project are cloned, or imported into an Eclipse Workspace, the ant view should be opened, and the Ant files for each of the projects should be added to the Ant view. Each Ant file is located at ./dist/build/ant.xml for the respective project. The `jar_and_bundle` task for each project (except `scratch-runner-main`) will build the bundle JAR for the project and automatically update the bundles installed in the `scratch-runner-main` project (at scratch-runner-main/dist/install/plain/bundle).

The `scratch-runner-main` project has a `run_test` task which will execute the compiled project against an SB2 file (scratch project file) located at scratch-runner-main/dist/install/test/testproj.sb2 (ensuring that the latest bundles installed at scratch-runner-main/dist/install/plain/bundle are used).

The `scratch-runner-main` project has a `create_deliverables` task which will create four ZIP files in scratch-runner-main/dist. These four zip files consist of the following:
* A generic Scratch Runner application package containing an executable jar for starting the Scratch Runner.
* A Windows-specific Scratch Runner application package which contains a more Windows-friendly batch file, and a couple of REG files for associating SB2 files with the Scratch Runner. These REG files need to be run manually (ie. no installer is provided yet, so you double-click on the REG file and merge it into the registry). One file (sb2.reg) defines the SB2 file extension for the operating system, and the other (sb2run.reg) creates a run command in the context (right-click) menu for the file. sb2run.reg should be modified to point to the correct location where you intend to install the Scratch Runner.
* A standard Java library.
* An OSGI Java library.
