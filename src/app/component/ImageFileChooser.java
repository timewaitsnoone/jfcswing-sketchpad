package app.component;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ImageFileChooser extends JFileChooser {
	public ImageFileChooser() {
		setAcceptAllFileFilterUsed(false);
		configExtensions();
	}
	private void configExtensions() {
		this.addChoosableFileFilter(new FileNameExtensionFilter("*.jpg", "JPEG"));
		this.addChoosableFileFilter(new FileNameExtensionFilter("*.png", "PNG"));
		this.addChoosableFileFilter(new FileNameExtensionFilter("*.bmp", "Bitmap"));
		this.addChoosableFileFilter(new FileNameExtensionFilter("*.tif", "TIFF"));
		this.addChoosableFileFilter(new FileNameExtensionFilter("*.ico", "ICO"));
	}

	/**
	 * Works around a JFileChooser limitation, that the selected file when saving
	 * is returned exactly as typed and doesn't take into account the selected
	 * file filter.
	 */
	public File getSelectedFileWithExtension() {
	    File file = super.getSelectedFile();
	    if (getFileFilter() instanceof FileNameExtensionFilter) {
	        String[] exts = ((FileNameExtensionFilter)getFileFilter()).getExtensions();
	        String nameLower = file.getName().toLowerCase();
	        for (String ext : exts) { // check if it already has a valid extension
	            if (nameLower.endsWith('.' + ext.toLowerCase())) {
	                return file; // if yes, return as-is
	            }
	        }
	        // if not, append the first one from the selected filter
	        file = new File(file.toString() + '.' + exts[0]);
	    }
	    return file;
	}
}