import java.nio.charset.*
import java.lang.*
import java.util.zip.CRC32

def crc32(byte[] bytes) {
    a = new CRC32().with { 
        update bytes
        value
        }
    return Long.toHexString(a)
}

flowFile = session.get()
if(!flowFile) return

def errorOccurred = false

try {
    eventId = flowFile.getAttribute('eventId')
    def eventBytes = eventId.getBytes(StandardCharsets.UTF_8)
    def hashEventId = crc32(eventBytes)
    flowFile = session.putAttribute(flowFile, 'hashedEventId', hashEventId)
} catch(e) {
    log.error('Something broke: ', e)
    errorOccurred = true
}

if(errorOccurred) {
  session.transfer(flowFile, REL_FAILURE)
}
else {
  session.transfer(flowFile, REL_SUCCESS)
}