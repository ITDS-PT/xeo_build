XEO Build System Instructions

#### Requirements
- ANT
- Java 1.6 (for compilation)

0) Checkout this project, clone the XEO and XWC Projects to your local hard drive
1) If the concatenator.jar does not exist, generate the JAR from the src folder (it should be in the lib folder)

2) Set the version and path to git repositories in ant/global.properties 
	- xeo.buildnumber -> Build number (ex: 001)
    - xeo.ver -> Major/minor version (ex: 4_0)
    - xwc.git -> Path to the XwC git repository (ex: /Users/user/git/xwc.git)
    - xeo.git -> Path to the XEO Core git repository (ex: /Users/user/git/xeo/.git)
    - xeo.languages (the set of languages the application supports, ex: en,pt,es)
    - xeo.branch (The branch to clone from the XEO git repository ex: master)
    - xwc.branch (The branch to clone from the XwC git repository ex: master)
3) Make sure you are using Java 6 to compile
4) Run ant -f build.xml to create necessary structures from the ant/directory
5) Run ant -f BuildProject.xml to generate the XAR file from the ant/directory
 
6) The generated XAR file will be located in a folder named after the build version (ex. 4_0_001/xeocore.xar)
 


NOTES : Currently it does not create tags in the repository nor does it copy the generated to XAR to anywhere