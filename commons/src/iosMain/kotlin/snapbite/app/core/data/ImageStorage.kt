/*



actual class ImageStorage {

    private val fileManager = NSFileManager.defaultManager
    private val documentDirectory = NSSearchPathForDirectoriesInDomains(
        directory = NSDocumentDirectory,
        domainMask = NSUserDomainMask,
        expandTilde = true
    ).first() as NSString

    actual suspend fun saveImage(bytes: ByteArray): String {
        return withContext(Dispatchers.Default) {
            val fileName = NSUUID.UUID().UUIDString + ".jpg"
            val fullPath = documentDirectory.stringByAppendingPathComponent(fileName)

            val data = bytes.usePinned {
                NSData.create(
                    bytes = it.addressOf(0),
                    length = bytes.size.toULong()
                )
            }

            data.writeToFile(
                path = fullPath,
                atomically = true
            )
            fullPath
        }
    }

    actual suspend fun getImage(fileName: String): ByteArray? {
        return withContext(Dispatchers.Default) {
            memScoped {
                NSData.dataWithContentsOfFile(fileName)?.let { bytes ->
                    val array = ByteArray(bytes.length.toInt())
                    bytes.getBytes(array.refTo(0).getPointer(this), bytes.length)
                    return@withContext array
                }
            }
            return@withContext null
        }
    }

    actual suspend fun deleteImage(fileName: String) {
        withContext(Dispatchers.Default) {
            fileManager.removeItemAtPath(fileName, null)
        }
    }
}*/
