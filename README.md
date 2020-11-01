# I'm Usually Partying

Authentication Service&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Party Service&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Users Service

[![Authentication Build Status](https://dev.azure.com/william20/IUP/_apis/build/status/AuthService?branchName=main)](https://dev.azure.com/william20/IUP/_build/latest?definitionId=3&branchName=main)
[![Party Build Status](https://dev.azure.com/william20/IUP/_apis/build/status/PartyService?branchName=main)](https://dev.azure.com/william20/IUP/_build/latest?definitionId=5&branchName=main)
[![Users Build Status](https://dev.azure.com/william20/IUP/_apis/build/status/UsersService?branchName=main)](https://dev.azure.com/william20/IUP/_build/latest?definitionId=4&branchName=main)

## Outline

The aim of the project is to help people socialize, by building an application that lets users create and participate to private events that can be organized by anyone. 

Users that act as hosts can set up an event by specifying a place and a date, and eventually set up additional prerequisites for joining the event, such as age, gender, dress code, entrance fee etc.  On the other hand, users that are looking for an event to join can see all the available events nearby and send requests to hosts.

To ensure safety and quality for the events, the application can be made to require some identification information, e.g. a Kennitala or the mobile phone number. Moreover, a feedback system can be implemented to evaluate hosts and participants. This feedback value can be used to evaluate whether to allow someone to join or to decide whether an event is really worth one's own time.

## Platform

The choice of building this application on a mobile platform provides several advantages. At its core the application will need the 
geolocalization (GPS) provided by smartphones to locate users on a map and compute distances between users and events. Then, smartphones offer also an easy and ideal choice for identification by using the owner's mobile number, if better identification methods are not available. Events might also be shared by QR codes which can be scanned by smartphone cameras. Lastly, smartphones provide the greatest degree of mobility and allow users to run the application whenever and wherever they are, a very important feature in our vision which would be greatly capitalized by the application.

## Business case

We believe this app might prove useful in organizing social events in a bottom-up, user-centered way, where users can decide to create their own event for anyone to join or just participate in one on the fly with ease. To date, we have not found any application that provides these functionalities, strengthening our belief that the novelty of the project might prove fruitful (more so in future times) when deployed to the Play Store.
