package at.spengergasse.hbgm.interfaces;

import java.io.File;

/**
 * a file loader is used for loading all the DICOM files
 * contained in the folder
 */
public interface IFileLoader extends IModuleBase {
    /**
     * load DICOM files of this folder,
     * assemble object model (GetPatients, Studies, ...)
     * an pass patients to patient repository
     * @param folder containing dicom files
     * @throws Exception if read errors occur
     */
    void LadeVerzeichnis(File folder) throws Exception;
}
