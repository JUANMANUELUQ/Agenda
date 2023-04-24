module Agenda2 {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.desktop;
	requires javafx.base;
	
	opens application to javafx.graphics, javafx.fxml;
	exports co.uniquindio.pr2.agenda.application to javafx.graphics;
	exports co.uniquindio.pr2.agenda.controllers to javafx.fxml;
	opens co.uniquindio.pr2.agenda.views to javafx.fxml;
	opens co.uniquindio.pr2.agenda.controllers to javafx.fxml;
}
