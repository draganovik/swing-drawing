# Swing Drawing - Design Patterns

## Project Requirements for the Course "Design Patterns"

This project involves implementing a desktop application for working with 2D graphics using **Java Swing**. The application should support functionalities developed during the "Object-Oriented Information Technologies" course, with additional requirements and enhancements outlined below.

### Key Features:

- **Shape Drawing with Custom Colors**
  Users should be able to draw shapes with different border and fill colors, using the _JColorChooser_ class.

- **Transparent Shape Interior**
  The interior of the circular shape should have a transparent hole (utilizing `java.awt.Graphics2D`, `java.awt.Shape`, `java.awt.geom.Area`, and `java.awt.geom.Ellipse2D`).

- **Naming Conventions**
  Class names, methods, and variables must follow English naming conventions.

- **MVC Architecture**
  The application should be designed using the **Model-View-Controller (MVC)** architectural pattern.

- **Hexagon Shape Operations**
  Adding, modifying, and deleting hexagons using the **Adapter** pattern for the provided `hexagon.jar`.

- **Undo and Redo Operations**
  Implement undo and redo functionality for executed commands using the **Command** and **Prototype** patterns. Undo/redo buttons should be enabled only when those operations are available.

- **Command Log Generation and Display**
  The app should generate and display a log of executed commands.

- **Save and Load Drawings & Logs**

  - Save the drawing log to an external file and reload it using **Serialization** and the **Strategy** pattern.
  - Load a drawing by reading the command log file, recreating the drawing step by step with user interaction.

- **Z-Order Position Changes**
  Users can change the shape position along the Z-axis, including:

  - _To Front_ (move one step forward)
  - _To Back_ (move one step backward)
  - _Bring to Front_ (move to the top)
  - _Bring to Back_ (move to the bottom)

- **Active Color Display**
  Display the current drawing colors for the border and fill. Clicking the color allows users to change the active color through a dialog.

- **Multi-Shape Selection**
  Enable selection of multiple shapes.

- **Conditional Delete Button**
  The delete button should only be enabled when there are selected objects (using the **Observer** pattern).

- **Conditional Modify Button**
  The modify button should only be enabled when a single shape is selected (using the **Observer** pattern).

### Application Logic:

- All user actions related to drawing should be logged, including shape selection and Z-order changes (_To Front_, _To Back_, _Bring to Front_, _Bring to Back_), as well as _undo_ and _redo_ actions.

- After modifying a selected shape, the shape should remain selected.

---

### Additional Notes:

- This project is **individual work**; each student must complete it independently.
- Using **GitHub** is mandatory. You are expected to commit frequently as you work on each task. Commit messages should clearly explain the changes made. Projects with only one or very few commits, or with all commits done within a short period, **will not be accepted**.

- Following the MVC pattern:

  - The **model** class contains the list of shapes.
  - The **view** is the `JPanel` where shapes are drawn.

  The solution must avoid global variables or using the **Singleton** pattern for referencing MVC classes. **This will not be accepted**.

### Implementation Overview:

- The log should capture all user actions, including selections and shape movements along the Z-axis.
- Undo/redo operations should be logged, and after modifying a selected shape, it should remain selected.

---

### Image Previews:

...coming soon...

---

### Getting Started:

- Clone the repository:
  ```bash
  git clone https://github.com/draganovik/swing-drawing.git
  ```
- Build the project using Maven:
  ```bash
  mvn clean install
  ```
- Run the application:
  ```bash
  java -jar target/drawing-app.jar
  ```

---

### Technologies & Patterns Used:

- **Java Swing**
- **MVC (Model-View-Controller)**
- **Adapter Pattern** for Hexagon shape operations
- **Command & Prototype Patterns** for undo/redo
- **Strategy Pattern** for file saving/loading
- **Observer Pattern** for enabling/disabling buttons based on selection
