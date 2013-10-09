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

"""Global Variables For Error Handling"""
LineCounter = 0

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

    for eventFile in eventFiles:
        parseEventFile(eventFile)


"""Event file parser"""
def parseEventFile(filePath): 
    global Events

    EventName = os.path.basename(filePath)
    EventName = EventName.partition('.')[0] #Remove the file extension


"""World file manager"""
def runParseWorld(folderName):
    global World
    global LineCounter

    LineCounter = 0

    rawTextList = [] #Store the text in a list for easier parsing

    if verbose:
        print 'Looking for the World File'

    worldFiles = getFiles(folderName, worldFileExtension)

    if (len(worldFiles) > 1):
        errorHandler('Too Many Worlds')

    else:
        with open(worldFiles[0], 'r') as worldFile:
            rawTextList = (line.rstrip() for line in worldFile) #no carriage returns
            rawTextList = list(line for line in rawTextList if line) #no blank lines

        World = parseWorld(rawTextList,0)

    print World


"""World file parser"""
def parseWorld(TagList,PreviousIndent):
    global LineCounter

    #If this is empty, get out before recursion eats your soul
    if TagList == []:
        return

    ParsedList = []

    LineIndex = 0 #We need to keep track of our position in the list

    while LineIndex < len(TagList):

        LineCounter += 1 #Increment for error handler

        LinesParsed = 1 #Reset

        #Count how far indented we are
        leading_spaces = len(TagList[LineIndex]) - len(TagList[LineIndex].lstrip())

        if leading_spaces == PreviousIndent: #The tag is on the same level as previously
            ParsedList.append(TagList[LineIndex].lstrip())
            LinesParsed = 1

        elif leading_spaces == PreviousIndent + 1: #Subtag of the previous tag
           
            #Find how far down the rabbit hole goes
            while 1:         
                if (LineIndex + LinesParsed >= len(TagList) - 1): #avoid overflow
                    break          
                NextIndent = len(TagList[LineIndex + LinesParsed]) - len(TagList[LineIndex + LinesParsed].lstrip())
                if NextIndent <= PreviousIndent:
                    break      
                LinesParsed += 1

            #construct a sub-list to parse
            SubTagList = TagList[LineIndex:LineIndex+LinesParsed]
            #woo recursion
            ParsedList.append(parseWorld(SubTagList,leading_spaces))

        else:
            errorHandler('Indent Error in world file, line ' + str(LineCounter))


        LineIndex += LinesParsed

    return ParsedList


"""Function to generate tag code from data structure"""
def generateTags():
    return


"""Function to generate event code from data structure"""
def generateEvents():
    return


"""Function to generate world code from data structure"""
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

    if 'No Universe' in token:
        if verbose:
            print 'No Universe with that name found. Exiting.\n'
        exit(1)

    elif 'Unexpected Symbol' in token:
        if verbose:
            print token
        exit(1)

    elif 'Too Many Worlds' in token:
        if verbose:
            print 'Error: Multiple .world files found.'
        exit(1)

    elif 'Indent Error' in token:
        if verbose:
            print token
        exit(1)

    else:
        print 'Error: Unhandled Error.'
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

