import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Waktu extends JLabel implements ActionListener {

    private final Timer timer;
    private final SimpleDateFormat formatWaktu;
    private final Date tanggalSaatIni;

    public Waktu() {
        timer = new Timer(1000, this);
        formatWaktu = new SimpleDateFormat("dd MMMM yyyy, HH:mm:ss");
        tanggalSaatIni = new Date();
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        perbaruiWaktuSaatIni();
        tampilkanWaktuSaatIni();
    }

    private void perbaruiWaktuSaatIni() {
        tanggalSaatIni.setTime(System.currentTimeMillis());
    }

    private void tampilkanWaktuSaatIni() {
        setText(formatWaktu.format(tanggalSaatIni));
    }
}
