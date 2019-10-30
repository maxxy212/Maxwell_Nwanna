# Maxwell_Nwanna

The Project was built with an expressive MVVM design model, maximizing the single responsibility principle.
The "activity" folder contains the activities used in the project.

The network folder contains tow folders (background and calls) : The ApicallHandler class is an abstract class used to hold Object data gotten from network calls. The ApiReader class is responsible for handling all network response and error handling. The Networking class contains static methods that consumes the android networking library. ServiceResultReceiver class is a helper class that recieves results from the background service used "JobIntentService".

The background folder: It contains all background services.
The calls folder: It acts like an interactor that handles all networking calls and logic.


