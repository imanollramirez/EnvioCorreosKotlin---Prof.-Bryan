package bryan.miranda.recuperacindecontrasenia

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Properties
import javax.mail.Message
import javax.mail.MessagingException
import javax.mail.PasswordAuthentication
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

suspend fun enviarCorreo(receptor: String, sujeto: String, mensaje: String) = withContext(
    Dispatchers.IO) {
    // Configuración del servidor SMTP
    val props = Properties().apply {
        put("mail.smtp.host", "smtp.gmail.com")
        put("mail.smtp.socketFactory.port", "465")
        put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory")
        put("mail.smtp.auth", "true")
        put("mail.smtp.port", "465")
    }

    // Iniciar Sesión
    val session = Session.getInstance(props, object : javax.mail.Authenticator() {
        override fun getPasswordAuthentication(): PasswordAuthentication {
            return PasswordAuthentication("20230065@ricaldone.edu.sv", "xxva wagw uhpg qnlr")
        }
    })

    // Enviar correo
    try {
        val message = MimeMessage(session).apply {
            setFrom(InternetAddress("20230065@ricaldone.edu.sv"))
            addRecipient(Message.RecipientType.TO, InternetAddress(receptor))
            subject = sujeto
            setText(mensaje)
        }
        Transport.send(message)
    } catch (e: MessagingException) {
        e.printStackTrace()
        println("Error al enviar código")
    }
}