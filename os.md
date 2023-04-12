# The Operating System

The purpose of the language is to make an easily-usable system for humans to work with computers.  No unstructured typing should be required whatsoever, and the system as a whole should be assisting the user with formulating requests.

The core flow of the operating system is as follows:

- You have some kind of object you're working with.
  - It is visualized using the associated 'visualizer' for the type.
  - The visualizer can show various operations you can perform on it.
  - Wrapping the visualizer are options for selecting a different visualizer and looking up other operations to apply to the object in the system.
- You perform operations on the object.
  - The visualizer picks the best way to do the operation.
  - By default, a simple window is shown with each input in a list.  Default editors for each type are used.  The operation is then performed with an "OK" button.
- Some operations result in new types - akin to moving screens.
- You can "reverse" operations with a back button.

## Concerns that reach into the technical realm

- Security by accessibility - the permission to access an object comes from the argument.
  - The "main" function has an input/output of `Root` for accessing all abilities the system has
  - Related - cannot de-anonymize a type to gain more permissions
- Progress-ability - every operation should be able to show its completion progress.
- Concatenative style - we need to view functions from the perspective of transforming types and ordered operations.

## Email Example

The `EmailAuthorization` type visualizes strictly as a set of options to perform.  One of these is to list recent emails, which results in the `Paginated[EmailWithAuth]` type.  This is visualized as a set of pages you can go back and forth on (that do indeed make more requests) and list the individual emails.  You click on an email, which is a `get` operation with a particular index, resulting in simply the `EmailWithAuth` type, which is visualized as simply the content of the email.  The visualizer also presents related functionality that might be desired, such as `reply`.

## General Visualizer Needs

- Back/Undo/History
- Switch visualizer for type
- Execute another command