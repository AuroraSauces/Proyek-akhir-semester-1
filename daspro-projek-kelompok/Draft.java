import java.util.Scanner;

public class Draft{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String username, password;
        String[] kelas = {"1A", "1B", "1C", "1D", "1E", "1F", "1G", "1H", "1I", "2A", "2B", "2C", "2D", "2E", "2F", "2G", "2H", "2I", "3A", "3B", "3C", "3D", "3E", "3F", "3G", "3H", "3I", "4A", "4B", "4C", "4D", "4E", "4F", "4G", "4H", "4I"};
        String[] mataPelajaran = {"Daspro", "PrakDaspro", "PKN", "Matematika", "CTPS", "K3", "BIG", "KTI"};

        boolean continueLogin = true;
        int [][] nilaiSiswa = new int [kelas.length][];
        String [][] namaSiswa = new String [kelas.length][];
        int [] angkatanSiswa = new int[kelas.length];

        while (continueLogin) {
            System.out.print("Masukkan Username: ");
            username = sc.nextLine();
            System.out.print("Masukkan Password: ");
            password = sc.nextLine();

            if (username.equalsIgnoreCase("Dosen") && password.equalsIgnoreCase("Admin")) {
                System.out.println("Login Dosen Berhasil");
                System.out.println("======================================");

                while (true) {
                    System.out.println("Pilih tindakan:");
                    System.out.println("1. Isi Nilai Mahasiswa");
                    System.out.println("2. Lihat Nilai Setiap Kelas");
                    System.out.println("3. Keluar");
                    System.out.print("Masukkan pilihan: ");
                    byte pilihan = sc.nextByte();
                    sc.nextLine(); 

                    switch (pilihan) {
                        case 1:
                            System.out.print("Pilih kelas (1A-4I): ");
                            String pilihKelasInput = sc.nextLine();
                            int indexKelas = getIndexKelas(pilihKelasInput);

                            if (indexKelas != -1) {
                                int tahunSekarang = 2023 - (indexKelas / 9);

                                System.out.print("Masukkan jumlah mahasiswa: ");
                                int mhsw = sc.nextInt();
                                sc.nextLine();

                                nilaiSiswa [indexKelas] = new int[mhsw];
                                namaSiswa [indexKelas] = new String[mhsw];
                                angkatanSiswa [indexKelas] = tahunSekarang;

                                for (int i = 0; i < mhsw; i++) {
                                    System.out.print("Masukkan nama mahasiswa " + (i + 1) + ": ");
                                    namaSiswa[indexKelas][i] = sc.nextLine();
                                    int totalNilai = 0;

                                    for (int j = 0; j < mataPelajaran.length; j++) {
                                        System.out.print("Masukkan nilai " + mataPelajaran[j] + " untuk " + namaSiswa[indexKelas][i] + ": ");
                                        int nilai = sc.nextInt();
                                        sc.nextLine(); 
                                        totalNilai += nilai;
                                    }


                                    nilaiSiswa [indexKelas][i] = totalNilai;
                                }

                                System.out.println("Pengisian nilai selesai. ");
                            } else {
                                System.out.println("Kelas tidak valid. ");
                            }
                            break;

                        case 2:
                            System.out.println("-------------------------------------------------------------------");
                            System.out.println("|\t\t         Nilai Setiap Kelas\t\t          |");
                            System.out.println("+-----------+--------+-------------------+------------------------+");
                            System.out.println("| Angkatan  | Kelas  | Nama Siswa        | Rata-rata Nilai        |");
                            System.out.println("+-----------+--------+-------------------+------------------------+");

                            for (int i = 0; i < kelas.length; i++) {
                                boolean adaDataNilai = false;

                                if (nilaiSiswa[i] != null) {
                                    for (int k = 0; k < nilaiSiswa[i].length; k++) {
                                        double rataNilai = (double) nilaiSiswa[i][k] / mataPelajaran.length;
                                        System.out.printf("| %-9s | %-6s | %-13s\t | %-15.2f\t  |\n", angkatanSiswa[i], kelas[i], namaSiswa[i][k], rataNilai);
                                        adaDataNilai = true;
                                    }
                                } 
                                
                                if (!adaDataNilai) {
                                    System.out.printf("| %-9s | %-6s | %-43s|\n", angkatanSiswa[i], kelas[i], "Belum ada data nilai untuk kelas ini.");

                                } 
                                System.out.println("+-----------+--------+-------------------+------------------------+");
                            }
                            break;


                        case 3 :
                        System.out.println("Keluar dari program.");
                        continueLogin = false;
                        break;

                        default:
                        System.out.println("Pilihan tidak valid.");
                        break;

                    }

                    if (pilihan == 3) {
                        break;
                    }
                    
                }
            } else {
                System.out.println("Login Gagal. Silakan coba lagi.");
            }
        } 

        sc.close();

    }

    public static int getIndexKelas(String kelasTerpilih) {
        if (kelasTerpilih.matches("[1-4][A-I]")) {
            int indexKelas = (kelasTerpilih.charAt(0) - '1') * 9 + (kelasTerpilih.charAt(1) - 'A');
            return indexKelas;
        } else {
            return -1;
        }
    }
      
}
