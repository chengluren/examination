package org.dreamer.examination.importer;

import java.io.File;

/**
 * @author lcheng
 * @version 1.0
 *          ${tags}
 */
public interface Importer {

    public void doImport(File file,long storeId);
}
