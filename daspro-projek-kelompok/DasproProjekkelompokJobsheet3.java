import java.util.Scanner;

public class DasproProjekkelompokJobsheet3 {
    private static double calculateRataNilai(int totalNilai, int jumlahMataPelajaran) {
        return (double) totalNilai / jumlahMataPelajaran;
    }

    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String Username, Password;
        String[] kelas = {"1A", "1B", "1C", "1D", "1E", "1F", "1G", "1H", "1I"};
        String[] mataPelajaran = {"Daspro", "prakdaspro", "pkn", "matematika", "ctps", "k3", "bahasa inggris", "kti"};

        boolean continueLogin = true;
        int[][][] nilaiSiswa = new int[kelas.length][3][];
        String[][] namaSiswa = new String[kelas.length][];

        while (continueLogin) {
            System.out.print("Masukkan Username : ");
            Username = sc.nextLine();
            System.out.print("Masukkan Password : ");
            Password = sc.nextLine();

            if (Username.equalsIgnoreCase("Dosen") && Password.equalsIgnoreCase("Admin")) {
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
                            // Implementasi pengisian nilai
                            System.out.println("Pilih kelas (1a-1i): ");
                            String pilihKelasInput = sc.nextLine();
                            int indexKelas = getIndexKelas(pilihKelasInput);

                            if (indexKelas != -1) {
                                System.out.println("Masukkan jumlah mahasiswa : ");
                                int mhsw = sc.nextInt();
                                sc.nextLine(); 

                                nilaiSiswa[indexKelas][0] = new int[mhsw]; //nilai tugas
                                nilaiSiswa[indexKelas][1] = new int[mhsw]; //nilai ujian
                                nilaiSiswa[indexKelas][2] = new int[mhsw]; //nilai praktek
                                namaSiswa[indexKelas] = new String[mhsw];

                                for (int i = 0; i < mhsw; i++) {
                                    System.out.println("Masukkan nama mahasiswa " + (i + 1) + " : ");
                                    namaSiswa[indexKelas][i] = sc.nextLine();
                                    int totalNilaiTugas = 0;
                                    int totalNilaiUjian = 0;
                                    int totalNilaiPraktek = 0;

                                    for (int j = 0; j < mataPelajaran.length; j++) {
                                        if (j < 2) { //tugas dan ujian
                                            System.out.println("Masukkan nilai " + mataPelajaran[j] + " untuk " + namaSiswa[indexKelas][i] + " : ");
                                            int nilai = sc.nextInt();
                                            sc.nextLine();
                                            if (j == 0) {
                                                totalNilaiTugas += nilai;
                                            } else {
                                                totalNilaiUjian += nilai;
                                            }
                                        } else { //praktek
                                            System.out.println("Masukkan nilai " + mataPelajaran[j] + " untuk " + namaSiswa[indexKelas][i] + " : ");
                                            int nilai = sc.nextInt();
                                            sc.nextLine();
                                            totalNilaiPraktek += nilai;
                                        }
                                    }

                                    // Simpan total nilai ke dalam array nilaiSiswa
                                    nilaiSiswa[indexKelas][0][i] = totalNilaiTugas;
                                    nilaiSiswa[indexKelas][1][i] = totalNilaiUjian;
                                    nilaiSiswa[indexKelas][2][i] = totalNilaiPraktek;
                                }

                                System.out.println("Pengisian nilai selesai.");
                            } else {
                                System.out.println("Kelas tidak valid.");
                            }
                            break;

                        // ... (bagian kode sebelumnya)

                        case 2:
                        System.out.println("Nilai Setiap Kelas:");
                        System.out.println("+-------+--------------+----------------+");
                        System.out.println("| Kelas | Nama Siswa   | Rata-rata Nilai |");
                        System.out.println("+-------+--------------+----------------+");
                        for (int i = 0; i < kelas.length; i++) {
                            if (nilaiSiswa[i][0] != null) {
                                for (int k = 0; k < nilaiSiswa[i][0].length; k++) {
                                    double rataTugas = calculateRataNilai(nilaiSiswa[i][0][k], 2);
                                    double rataUjian = calculateRataNilai(nilaiSiswa[i][1][k], 2);
                                    double rataPraktek = calculateRataNilai(nilaiSiswa[i][2][k], 1);
                                    double rataNilai = (rataTugas + rataUjian + rataPraktek) / 3;
                                    System.out.printf("| %-5s | %-12s | %-15.2f |\n", kelas[i], namaSiswa[i][k], rataNilai);
                                }
                            } else {
                                System.out.println("| " + kelas[i] + " | Belum ada data nilai untuk kelas ini. | -              |");
                            }
                            System.out.println("+-------+--------------+----------------+");
                        }
                        break;

// ... (bagian kode setelahnya)

                        case 3:
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

    static int getIndexKelas(String kelasTerpilih) {
        // Mengonversi input 1a-1i menjadi indeks
        char kelasChar = kelasTerpilih.charAt(0);
        char subKelasChar = kelasTerpilih.charAt(1);

        if (kelasChar >= '1' && kelasChar <= '9' && subKelasChar >= 'a' && subKelasChar <= 'i') {
            int indexKelas = (kelasChar - '1') * 9 + (subKelasChar - 'a');
            return indexKelas;
        } else {
            return -1; 
        }
    }
}
