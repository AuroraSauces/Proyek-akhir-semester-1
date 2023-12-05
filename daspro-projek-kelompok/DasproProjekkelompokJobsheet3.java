import java.util.Scanner;

public class DasproProjekkelompokJobsheet3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String Username,Password;
        String[] namasiswa;
        int[][] nilai;
        int siswa;
        String[] mataPelajaran = {
            "Daspro" , "prakdaspro" , "pkn" , "matematika" , "ctps" , "k3" , "bahasa inggris" , "kti"
        };

        while (true) {
            System.out.print("Masukkan Username : ");
            Username = sc.nextLine();
            System.out.print("Masukkan Password : ");
            Password = sc.nextLine();

            if (Username.equalsIgnoreCase("Dosen") && Password.equalsIgnoreCase("Admin")){
                System.out.println("Login Dosen Berhasil");
                System.out.println("Masukkan jumlah siswa");
                siswa = sc.nextInt();
                namasiswa = new String[siswa];
                nilai = new int[siswa][mataPelajaran.length];

                for (int i = 0; i < siswa; i++) {
                    System.out.println("Nama");
                    namasiswa[i] = sc.next();
                    for (int j = 0; j < mataPelajaran.length; j++) {
                        System.out.print("Masukkan nilai " + mataPelajaran[j] + ": ");
                        nilai[i][j] = sc.nextInt();
                    }
                }

                for (int i = 0; i < siswa; i++) {
                    int sum = 0;
                    for (int j = 0; j < mataPelajaran.length; j++) {
                        sum += nilai[i][j];
                    }
                    int Nilaiakhir = sum / mataPelajaran.length;

                    if (Nilaiakhir > 85) {
                        System.out.println(namasiswa[i] + ": Nilai akhir " + Nilaiakhir + " Bernilai A");
                    } else if (Nilaiakhir > 80) {
                        System.out.println(namasiswa[i] + ": Nilai akhir " + Nilaiakhir + " Bernilai B+");
                    } else if (Nilaiakhir > 75) {
                        System.out.println(namasiswa[i] + ": Nilai akhir " + Nilaiakhir + " Bernilai B");
                    } else if (Nilaiakhir > 70) {
                        System.out.println(namasiswa[i] + ": Nilai akhir " + Nilaiakhir + " Bernilai C");
                    } else {
                        System.out.println(namasiswa[i] + ": Nilai akhir " + Nilaiakhir + " Bernilai D");
                    }
                }
            } 

            System.out.println("Apakah ingin kembali login? y/n");
            String choice = sc.nextLine();

            if (!choice.equalsIgnoreCase("y")){
                break;
            }
        }
    }
}
