Original App Design Project - README Template
===

# Pet Reminder App

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
2. [Schema](#Schema)

## Overview
### Description
This app helps people take care of their pets by giving reminders for certain tasks they need to complete such as walking the dog or refilling the water bowl. 
Users can also collaborate on caregiving for a pet, allowing users to see when another caregiver has completed a task for the pet.
For example, if your brother wakes up at 6am and takes the dog out and you wake up at 9am, you could check the app to see that you don't need to take them out since your brother marked the task completed.
### App Evaluation
[Evaluation of your app across the following attributes]
- **Category:** Productivity
- **Mobile:** This app would be developed for mobile as that is the most convenient for users, considering how they have more access to a mobile device more often than a computer.
- **Story:** Reminds people to take care of their pets. People can collaborate on task completion for a pet.
- **Market:** People who have a pet to take care of
- **Habit:** This app would be used multiple times per day. The user would have reminders to complete tasks for their pet, and they would check off those tasks once they are complete
- **Scope:** The app would start off as a collaborative pet task reminder app. We would like to later on add features for dog owners displaying nearby dog parks as well as a way for dog owners to meet each other at these parks.

## Product Spec

### 1. User Stories (Required and Optional)

**Required Must-have Stories**

* User can see list of pets they manage
* User can create recurring tasks for a pet
* User can receive reminders for tasks
* Users can collaborate together on tasks for a pet

**Optional Nice-to-have Stories**

* User can mute notifs. for cat
* User can see more info about a pet when clicking their icon (Do's and don'ts)
* Add a chat to communicate between owners and petsitters

### 2. Screen Archetypes

* Sign up/Login screen
   * Login to collaborate with users
   * Option to use google login, etc
* Home screen
   * List all pets and/with tasks
   * Visable how important tasks are

### 3. Navigation

**Tab Navigation** (Tab to Screen)
* [Main tab with all the task]
* [Adding people and pets]
* [Chat tab]


**Flow Navigation** (Screen to Screen)

* [Sign up/Login Screen]
   * [-> Home Screen]
* [Home Screen]
   * [-> Pet Detail Screen]
   * [-> Pet Creation Screen]
   * [-> Add Friend Screen]

## Wireframes
[Add picture of your hand sketched wireframes in this section]
<img src="YOUR_WIREFRAME_IMAGE_URL" width=600>

### [BONUS] Digital Wireframes & Mockups

### [BONUS] Interactive Prototype

## Schema 
[This section will be completed in Unit 9]
### Models

#### Tasks
| Property      | Type          | Description                        |
| ------------- |:-------------:| :-----|
| Time          | DateTime      | Times user wants app to alarm them |
| Description   | String        | Inputted by user to describe tasks |
| Title         | String        | Title of task |
| Completed     | Boolean       | Whether the task is complete or not |
| Repeat        | String        | How often to show the task/notify the user |

### Networking
- [Add list of network requests by screen ]
- [Create basic snippets for each Parse network request]
- [OPTIONAL: List endpoints if using existing API such as Yelp]
