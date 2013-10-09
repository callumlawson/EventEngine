"""=====================================================================
Eventful Engine Parsing Script
====================================================================="""

"""Setup ============================================================"""

"""Imports and dependencies"""
import sys #Command line args
import os #Filepaths


"""Configuration Variables (Set to defaults)"""
verbose = False
parseTags = True
parseEvents = True
parseWorld = True
runHelp = False


"""File Extension Types"""
tagFileExtension = '.tags'
eventFileExtension = '.event'
worldFileExtension = '.world'
universeDirExtension = '.universe'

"""Argument definitions"""
universeName = sys.argv[1]
if '-v' in sys.argv:
    verbose = True
if '-notags' in sys.argv:
    parseTags = False
if '-noevents' in sys.argv:
    parseEvents = False
if '-noworld' in sys.argv:
    parseWorld = False
if ('-h' in sys.argv) or ('-help' in sys.argv):
    runHelp = True

"""Lists initialisation"""
Tags = [] #The first value of a sublist is the tag type name
Events = [] #The first value of a sublist is the event name
World = []



"""Function Definitions ============================================="""

"""Tag file sorter and parsing manager"""
def runParseTags(folderName):
    global Tags

    if verbose:
        print 'Looking for Tag Files...'
	
    tagFiles = getFiles(folderName, tagFileExtension)
    
    for tagFile in tagFiles:
        ParseTagFile(tagFile)



"""Tag file parser"""
def ParseTagFile(filePath):
    global Tags
    
    TagType = os.path.basename(filePath)
    TagType = TagType.partition('.')[0] #Remove the file extension

    LocalTags = []
    
    with open( filePath , 'r' ) as tagfile:
        LocalTags = (line.rstrip() for line in tagfile) #no whitespace
        LocalTags = list(line for line in LocalTags if line) #no blanks

    LocalTags.insert(0, TagType) #Make the tag type the first value

    if verbose:
        print LocalTags

    Tags.append(LocalTags) #Add to the global list
    


"""Event file sorter and parsing manager"""
def runParseEvents(folderName):
    global Events

    if verbose:
        print 'Looking for Event Files'

    eventFiles = getFiles(folderName, eventFileExtension)


"""Event file parser"""
def ParseEventFile(filePath): 
    print 'Parsing event file here'


"""World file manager and parser"""
def runParseWorld(folderName):
    print 'Parsing world file here'


def generateTags():
    return


def generateEvents():
    return


def generateWorld():
    return
	

"""File fetching by extension"""
def getFiles(folderName, fileExtension):

    foundFiles = []
    for dirName, subdirList, fileList in os.walk(folderName):
        for fname in fileList:
            if fileExtension in fname:
                if verbose:
                    print ('Found \t%s' % fname)
                foundFiles.append(os.path.join(dirName,fname))
    return foundFiles

"""Utility Functions ================================================"""

"""Help Function"""
def printHelp():
    print '\n\nParser for Eventful Engine\n'
    print 'Usage: Run parser.py from the command line with your project name as the argument'
    print '\nOptional Arguments:'
    print '-v\t\tverbose mode'
    print '-noworld\tdon\'t parse world files'
    print '-notags\t\tdon\'t parse tag files'
    print '-noevents\tdon\'t parse event files'

"""Error Handling Function"""
def errorHandler(token):
    if (token == 'No Universe'):
        if verbose:
            print 'No Universe with that name found. Exiting.\n'
        exit(1)
    elif 'Unexpected Symbol' in token:
        if verbose:
            print token
        exit(1)



"""Main ============================================================="""

if runHelp:
    printHelp()
    exit(0)

if verbose:
    print '\n\nParser running in verbose mode\n'

"""Check file validity"""
folderName = universeName + universeDirExtension
if os.path.exists(folderName) is False:
    errorHandler('No Universe')
if verbose:
    print ('Found ' + folderName + '\n')

"""Run the parsers and generators"""
if parseTags:
    runParseTags(folderName)
    generateTags()

if parseEvents:
    runParseEvents(folderName)
    generateEvents()

if parseWorld:
    runParseWorld(folderName)
    generateWorld()

if verbose:
    print 'Succesfully parsed files and generated code. Exiting.\n'
exit(0)

