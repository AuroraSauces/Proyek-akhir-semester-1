import java.util.Scanner;

public class Draft {
    private static double calculateRataNilai(int totalNilai, int jumlahMataPelajaran) {
        return (double) totalNilai / jumlahMataPelajaran;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String Username, Password;
        String[] kelas = {"1A", "1B", "1C", "1D", "1E", "1F", "1G", "1H", "1I"};
        String[] mataPelajaran = {"Daspro", "prakdaspro", "pkn", "matematika", "ctps", "k3", "bahasa inggris", "kti"};

        boolean continueLogin = true;
        int[][] nilaiSiswa = new int[kelas.length][];

        while (continueLogin) {
            System.out.print("Masukkan Username : ");
            Username = sc.nextLine();
            System.out.print("Masukkan Password : ");
            Password = sc.nextLine();

            if (Username.equalsIgnoreCase("Dosen") && Password.equalsIgnoreCase("Admin")) {
                System.out.println("Login Dosen Berhasil");
                System.out.println("======================================");

                while (true) {  // Loop untuk memungkinkan pengisian kelas lebih dari satu kali
                    System.out.println("Pilih tindakan:");
                    System.out.println("1. Isi Nilai Mahasiswa");
                    System.out.println("2. Lihat Nilai Setiap Kelas");
                    System.out.println("3. Keluar");
                    System.out.print("Masukkan pilihan: ");
                    byte pilihan = sc.nextByte();
                    sc.nextLine(); // Membersihkan buffer masukan setelah nextByte

                    switch (pilihan) {
                        case 1:
                            // Implementasi pengisian nilai
                            System.out.println("Pilih kelas (1a-1i): ");
                            String pilihKelasInput = sc.nextLine();
                            int indexKelas = getIndexKelas(pilihKelasInput);

                            if (indexKelas != -1) {
                                System.out.println("Masukkan jumlah mahasiswa : ");
                                int mhsw = sc.nextInt();
                                sc.nextLine(); // Membersihkan buffer masukan setelah nextInt()

                                nilaiSiswa[indexKelas] = new int[mhsw];

                                for (int i = 0; i < mhsw; i++) {
                                    System.out.println("Masukkan nama mahasiswa " + (i + 1) + " : ");
                                    String namaSiswa = sc.nextLine();
                                    int totalNilai = 0;

                                    for (int j = 0; j < mataPelajaran.length; j++) {
                                        System.out.println("Masukkan nilai " + mataPelajaran[j] + " untuk " + namaSiswa + " : ");
                                        int nilai = sc.nextInt();
                                        sc.nextLine(); // Membersihkan buffer masukan setelah nextInt()
                                        totalNilai += nilai;
                                    }

                                    // Simpan total nilai ke dalam array nilaiSiswa
                                    nilaiSiswa[indexKelas][i] = totalNilai;
                                }

                                System.out.println("Pengisian nilai selesai.");
                            } else {
                                System.out.println("Kelas tidak valid.");
                            }
                            break;

                            case 2:
    for (int i = 0; i < kelas.length; i++) {
        for (int j = 0; j < kelas[i].length; j++) {
            System.out.println("Kelas " + kelas[i][j] + ":");
            if (nilaiSiswa[i] != null) {
                for (int k = 0; k < nilaiSiswa[i].length; k++) {
                    System.out.println("Nama mahasiswa " + (k + 1) + ": " + namaSiswa[i][k]);
                    System.out.println("Rata-rata Nilai: " + calculateRataNilai(nilaiSiswa[i][k], mataPelajaran.length));
                }
            } else {
                System.out.println("Belum ada data nilai untuk kelas ini.");
            }
        }
    }
    break;


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

    private static int getIndexKelas(String kelasTerpilih) {
        // Mengonversi input 1a-1i menjadi indeks
        char kelasChar = kelasTerpilih.charAt(0);
        char subKelasChar = kelasTerpilih.charAt(1);

        if (kelasChar >= '1' && kelasChar <= '9' && subKelasChar >= 'a' && subKelasChar <= 'i') {
            int indexKelas = (kelasChar - '1') * 9 + (subKelasChar - 'a');
            return indexKelas;
        } else {
            return -1; // Mengembalikan -1 jika kelas tidak valid
        }
    }
}
