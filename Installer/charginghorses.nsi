/*
 * charginghorses.nsi
 *
 * This script is designed to install all necessary files for the Charging
 * Horses software.
 *
 * Installation software written by Blaise Cannon.
 */

;--------------------------------

; this section runs pre-installation functions

!ifdef HAVE_UPX ; checks for UPX compression tool
!packhdr tmp.dat "upx\upx -9 tmp.dat"
!endif

!ifdef NOCOMPRESS ; checks for and sets relevant compression
SetCompress off
!endif

FileBufSize 128MB ; increases the default RAM buffer from 32MB to 128MB
AllowRootDirInstall false ; prevents installation directly to root folder
ShowInstDetails show ; shows install log
ShowUninstDetails show ; shows uninstall log
SetOverwrite off ; prevents file overwriting to protect the database

;--------------------------------

; this section generates the splash screen

Function .onInit

	SetOutPath $TEMP ; set path to temp... temporarily
	File /oname=spltmp.bmp "horse_splash.bmp" ; the image used by the splash plugin
	advsplash::show 2000 1000 1000 -1 $TEMP\spltmp ; time shown | time fade in | time fade out | transparent color | where files are
	Pop $0 ; register $0 has '1' if the user closed the splash screen early, '0' if everything closed normally, and '-1' if some error occurred.
	Delete $TEMP\spltmp.bmp ; deletes the temporary files from... temp

FunctionEnd

;--------------------------------

; this section creates all the necessary data for the GUI

Name "Charging Horses" ; executable title
Caption "Version 1.0" ; subtitle
Icon "horse.ico" ; graphical icon for exe
OutFile "Charging Horses.exe" ; output exe name

SetDateSave on ; saves the change date
SetDatablockOptimize on ; helps with compression
CRCCheck on ; checks for file errors and corruption before installation
SilentInstall normal ; uses default settings for silent installations
BGGradient 3DEAF0 000000 000000 ; the background gradient RGB RGB RGB in hex
InstallColors 0080FF 000030 ; colors used for the UI, default gray
XPStyle on ; sets XP manifest on or off, makes the installer controls use the new XP style when running on Windows XP

InstallDir "$PROGRAMFILES\Charging Horses" ; install directory
InstallDirRegKey HKLM "Software\ChargingHorses" "Install_Dir" ; installs a registry key for the software

CheckBitmap "Contrib\Graphics\Checks\classic-cross.bmp" ; this is for the checkmark box graphics

LicenseBKColor /windows ; sets license text background color to windows default
LicenseText "Welcome to the Charging Horses installer. Please read and agree to the license terms to continue." ; the window message for licensing
LicenseData "license" ; the license directory

RequestExecutionLevel admin ; prompts for Windows UAC permissions

;--------------------------------

; this section creates the necessary pages

Page license
Page components
Page directory
Page instfiles

UninstPage uninstConfirm ; popup that confirms uninstall
UninstPage uninstConfirm ; confirms uninstall
UninstPage instfiles

;--------------------------------

; this section deals with the registry

Section "-Write Registry Info" ; empty string makes it hidden, as does starting with -

	; write install strings
	DetailPrint "Debugging info: $1"
	WriteRegStr HKLM SOFTWARE\ChargingHorses "Install_Dir" "$INSTDIR"

	SetOutPath $INSTDIR ; sets path to install directory
	WriteUninstaller "horse-uninst.exe" ; writes uninstaller

	; write uninstall strings
	WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\ChargingHorses" "DisplayName" "Charging Horses Uninstaller"
	WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\ChargingHorses" "UninstallString" '"$INSTDIR\horse-uninst.exe"'

SectionEnd

;--------------------------------

; this section deals with the install types

!ifndef NOINSTTYPES ; if no install types defined

	InstType "Full" ; installs everything

	InstType "Required Only" ; only required files

!endif

AutoCloseWindow false ; auto-closes the installer after finishing

;--------------------------------

; this section is where we install the actual files

SectionGroup "Main Files"

	Section "Java" JAVA ; installs the JRE

		MessageBox MB_OK "Welcome to Charging Horses. This installation package will now attempt to install all required packages needed for operation. Please continue through the next few pages to complete the install. In order to complete the installation, you may simply click the 'next' button for most of the installation screens."
		SetOutPath "$INSTDIR"
		DetailPrint "Starting the Java Runtime Environment installer..."
		SectionIn RO
		File "jre.exe"
		ExecWait "$INSTDIR\jre.exe"
		Delete "$INSTDIR\jre.exe"

	SectionEnd

	Section "H2 Database" H2D ; installs the H2 database

		; tells user to install the required components

		SectionIn RO ; makes read-only
		SetOutPath "C:\H2" ; installs to given path
		File /nonfatal /a /r "H2\" ; installs H2

	SectionEnd

	Section "Setup" MAIN ; installs the primary GUI based program

		SetOutPath "$INSTDIR"
		DetailPrint "Starting the primary Charging Horses installer..."
		SectionIn RO
		File "setup.exe"
		ExecWait "$INSTDIR\setup.exe"
		Delete "$INSTDIR\setup.exe"

	SectionEnd

SectionGroupEnd

SectionGroup "Database Files"

	Section "-First Copy" FC ; copies the text file necessary to get database working

		SetOutPath "$INSTDIR"
		DetailPrint "Copying text file needed for first run..."
		SectionIn RO
		File "database.txt"

	SectionEnd

	Section "-First Run" FR ; runs the text file necessary to get database working

		SetOutPath "$INSTDIR"
		DetailPrint "Running text file needed for first run..."
		SectionIn RO
		File "first.bat"
		ExecWait "first.bat"
		Delete "first.bat"

	SectionEnd

	Section "Table Creation" TABL ; file that will make the tables

		SetOutPath "$INSTDIR"
		DetailPrint "Copying table creation file..."
		SectionIn RO
		File "CreateTables.class"

	SectionEnd

	Section "Database Setup" DBSU ; runs the file necessary to get the database tables running

		SetOutPath "$INSTDIR"
		DetailPrint "Running run.bat file..."
		SectionIn RO
		File "run.bat"
		ExecWait "run.bat"
		Delete "run.bat"

	SectionEnd

SectionGroupEnd

SectionGroup "Optional Files"

	Section "Readme" RME ; adds the readme to the install directory

		SectionIn 1
		SetOutPath "$INSTDIR"
		DetailPrint "Adding readme file to install directory..."
		File "README.txt"

	SectionEnd

	Section "License" LIC ; adds the license to the install directory

		SectionIn 1
		SetOutPath "$INSTDIR"
		DetailPrint "Adding license to install directory..."
		File "LICENSE.txt"

	SectionEnd

SectionGroupEnd

;--------------------------------

; Uninstaller

UninstallText "This will uninstall Charging Horses. Click next to continue the removal process."
UninstallIcon "Uninstall.ico"

Section "Uninstall"

	DeleteRegKey HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\ChargingHorses"
	DeleteRegKey HKLM "SOFTWARE\ChargingHorses"
	RMDir /r "$SMPROGRAMS\ChargingHorses" ; recursively removes start menu directory and files
	RMDir /r "$INSTDIR" ; recursively removes install directory and files
	RMDir /r "C:\H2" ; recursively removes H2 directory and files

SectionEnd
