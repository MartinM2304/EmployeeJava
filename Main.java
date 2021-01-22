import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {
public static void main(String[] args) {
		
		
		//JPanel panel = new JPanel();
		//JTextField text = new JTextField();
		
		SwingUtilities.invokeLater(new Runnable () {
			public void run (){		
		JFrame frame = new MainFrame("Company personal");		
		frame.setVisible(true);
		frame.setSize(1000, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
			}
		});
		
		

	}

}
