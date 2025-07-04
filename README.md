# Pepper Virtual Assistant

## Overview

Pepper was a Java-based virtual assistant that demonstrated the integration of speech recognition, natural language understanding, text-to-speech synthesis, and hardware control. 

## 🚀 Key Features

### Core Functionality
- **Speech Recognition**: Real-time voice input processing using CMU Sphinx
- **Text-to-Speech Synthesis**: Natural voice output using FreeTTS with MBROLA voice models
- **Natural Language Processing**: Intelligent conversation handling via AIML (Artificial Intelligence Markup Language)
- **Multi-modal Interface**: Support for both voice commands and text input
- **Rich GUI**: Modern Swing-based interface with HTML rendering for enhanced chat visualization

### Advanced Capabilities
- **Media Control**: Integrated MP3 playback with random music selection
- **System Integration**: Browser launching and application control
- **Hardware Interface**: Arduino connectivity for physical device control
- **Real-time Processing**: Continuous speech recognition with immediate response generation
- **Extensible AI**: AIML-based knowledge base for conversational intelligence

## 🏗️ Architecture

### Core Components

#### 1. Main Application (`Main.java`)
The central orchestrator that integrates all system components:
- **GUI Management**: Creates and manages the primary user interface
- **Speech Pipeline**: Coordinates speech recognition and synthesis
- **Command Processing**: Handles voice commands and text input
- **Response Generation**: Manages AIML-based conversation logic
- **Media Integration**: Controls music playback and system applications

#### 2. Speech Recognition System (`mic.java`)
Dedicated microphone interface providing:
- **Real-time Audio Capture**: Continuous listening capabilities
- **Voice Activity Detection**: Intelligent start/stop recognition
- **Command Recognition**: Processes spoken commands using CMU Sphinx
- **Visual Feedback**: Interactive interface with microphone controls

#### 3. Text-to-Speech Engine (`TTS.java`)
Sophisticated speech synthesis system featuring:
- **MBROLA Voice Integration**: High-quality voice synthesis
- **Configurable Voice Models**: Support for multiple voice personalities
- **Real-time Speech Generation**: Immediate audio feedback
- **Text Processing**: Handles various input formats and languages

#### 4. Media Management (`Mp3Player.java`)
Robust audio playback system with:
- **Format Support**: MP3 audio file playback
- **Random Selection**: Automatic music library management
- **Playback Control**: Play, stop, and close operations
- **Error Handling**: Graceful handling of audio processing errors

#### 5. Hardware Integration (`Blink.java`, `arduinoJava.java`)
Arduino connectivity framework providing:
- **Serial Communication**: Robust Arduino interface
- **GPIO Control**: Digital pin manipulation for LED and sensor control
- **Real-time Hardware Response**: Immediate physical feedback
- **Modular Design**: Extensible for additional hardware components

#### 6. AI Knowledge Management (`AddAiml.java`)
AIML bot configuration and management:
- **Dynamic Pattern Loading**: Runtime AIML file processing
- **Knowledge Base Management**: Organized conversation patterns
- **Response Generation**: Context-aware reply synthesis
- **Extensible Intelligence**: Easy addition of new conversation patterns

## 🛠️ Technology Stack

### Core Technologies
- **Java SE**: Primary development platform
- **Swing**: GUI framework for cross-platform interface
- **CMU Sphinx**: Speech recognition engine
- **FreeTTS**: Text-to-speech synthesis
- **MBROLA**: High-quality voice models
- **AIML**: Artificial Intelligence Markup Language for NLP

### External Libraries
- **Program AB**: AIML bot processing framework
- **JLayer**: MP3 audio processing
- **JArduino**: Arduino communication library
- **GNU RXTX**: Serial communication for hardware integration

### Hardware Integration
- **Arduino Platform**: Microcontroller interface
- **Audio Devices**: Microphone and speaker management
- **Serial Communication**: USB and Bluetooth connectivity

## 📁 Project Structure

```
Pepper/
├── Main.java              # Primary application controller
├── TTS.java               # Text-to-speech engine
├── mic.java               # Microphone interface
├── Mp3Player.java         # Audio playback system
├── Blink.java             # Arduino LED control
├── AddAiml.java           # AIML bot management
├── arduinoJava.java       # Arduino integration
├── VoiceLauncher.java     # Voice command launcher
├── testChat.java          # Chat system testing
├── testUI.java            # User interface testing
├── corpus.txt             # Voice command vocabulary
├── 1457.dic               # Speech recognition dictionary
├── 1457.lm                # Language model
└── src/main/resources/    # AIML knowledge base
```

## 🎯 Supported Commands

### Voice Commands
- **"Hi Pepper"** / **"Hello Pepper"**: Initialize conversation
- **"Play some music"**: Start random music playback
- **"Stop music"**: Halt current audio playback
- **"Open browser"**: Launch default web browser
- **Personal queries**: Name, age, preferences (handled by AIML)

### Text Interface
- Full natural language conversation support
- Question-answering capabilities
- Personal information management
- Context-aware responses

## 🚦 Getting Started

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Arduino IDE (for hardware features)
- MBROLA voice models
- Microphone and speakers

### Installation
1. Clone the repository
2. Configure MBROLA voice models in the system path
3. Install required libraries and dependencies
4. Compile Java source files
5. Run the main application

### Usage
1. Launch the application using `java Main`
2. Say "Hi Pepper" to activate voice mode
3. Use voice commands or type messages in the interface
4. Enjoy conversational interaction with your virtual assistant
