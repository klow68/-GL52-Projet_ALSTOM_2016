package MainMenu;

import java.net.URL;
import java.util.ResourceBundle;

import InterfaceAdmin.InterfaceAdminView;
import InterfaceAdmin.InterfaceAdminViewModel;
import InterfaceTech.InterfaceTechView;
import InterfaceTech.InterfaceTechViewModel;
import ModelObject.GestionnaireConfig;
import ObjectData.GestionnaireDonnees;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.ViewTuple;
import de.saxsys.mvvmfx.FluentViewLoader.FxmlViewStep;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The Class MainMenuView.
 */
public class MainMenuView implements FxmlView<MainMenuViewModel>, Initializable {

	/** The view model. */
	@InjectViewModel
	private MainMenuViewModel viewModel;
	
	/** The ap. */
	@FXML
	private VBox ap;

	/**
	 * Close app.
	 */
	@FXML
	private void closeApp() {
		viewModel.closeApp();
	}

	/**
	 * About.
	 */
	@FXML
	private void about() {
		viewModel.about();
	}

	/**
	 * Change window to admin.
	 */
	@FXML
	private void changeWindowToAdmin() {

		Stage stage = new Stage();
		FxmlViewStep<InterfaceAdminView, InterfaceAdminViewModel> view = FluentViewLoader
				.fxmlView(InterfaceAdminView.class);
		ViewTuple<InterfaceAdminView, InterfaceAdminViewModel> viewTuple = view.load();
		Parent root = viewTuple.getView();
		stage.setScene(new Scene(root));
		((Stage) ap.getScene().getWindow()).close();
		stage.show();

	}

	/**
	 * Change window to technicien.
	 */
	@FXML
	private void changeWindowToTechnicien() {
		Stage stage = new Stage();
		FxmlViewStep<InterfaceTechView, InterfaceTechViewModel> view = FluentViewLoader
				.fxmlView(InterfaceTechView.class);
		ViewTuple<InterfaceTechView, InterfaceTechViewModel> viewTuple = view.load();
		Parent root = viewTuple.getView();
		stage.setScene(new Scene(root));
		((Stage) ap.getScene().getWindow()).close();
		stage.show();

	}

	/** {@inheritDoc} */
	public void initialize(URL location, ResourceBundle resources) {
		GestionnaireConfig GC = new GestionnaireConfig();
		GC.run();
		GestionnaireDonnees GD = new GestionnaireDonnees();
		GD.run(GC);

	}

}
