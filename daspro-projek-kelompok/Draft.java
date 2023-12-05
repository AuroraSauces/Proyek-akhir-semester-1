import java.util.Scanner;

public class Draft {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String Username, Password;
        String[][] kelas = {
                {"1A", "1B", "1C", "1D", "1E", "1F", "1G", "1H", "1I"},
        };
        String[] mataPelajaran = {
                "Daspro", "prakdaspro", "pkn", "matematika", "ctps", "k3", "bahasa inggris", "kti"
        };

        boolean continueLogin = true;
        int[][] nilaiSiswa = new int[kelas.length][];

        while (continueLogin) {
            System.out.print("Masukkan Username : ");
            Username = sc.nextLine();
            System.out.print("Masukkan Password :");
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
                            System.out.println("Pilih tingkat");
                            System.out.println("1. Tingkat 1");
                            System.out.print("Masukkan Tingkat : ");
                            byte pilihTingkat = sc.nextByte();
                            sc.nextLine(); // Membersihkan buffer masukan setelah nextByte

                            if (pilihTingkat >= 1 && pilihTingkat <= kelas.length) {
                                for (int i = 0; i < kelas[pilihTingkat - 1].length; i++) {
                                    System.out.print(kelas[pilihTingkat - 1][i] + " ");
                                }
                                System.out.println("\nPilih kelas : ");
                                String kelasTerpilih = sc.nextLine();

                                if (isValidKelas(kelasTerpilih, kelas[pilihTingkat - 1])) {
                                    System.out.println("Masukkan jumlah mahasiswa : ");
                                    int mhsw = sc.nextInt();
                                    sc.nextLine(); // Membersihkan buffer masukan setelah nextInt()

                                    nilaiSiswa[pilihTingkat - 1] = new int[mhsw];

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
                                        nilaiSiswa[pilihTingkat - 1][i] = totalNilai;
                                    }

                                    System.out.println("Pengisian nilai selesai.");
                                } else {
                                    System.out.println("Kelas tidak valid.");
                                }
                            } else {
                                System.out.println("Tingkat tidak valid.");
                            }
                            break;

                        case 2:
                            // Menampilkan nilai setiap kelas
                            System.out.println("Nilai Setiap Kelas:");
                            for (int i = 0; i < kelas.length; i++) {
                                System.out.println("Tingkat " + (i + 1));
                                for (int j = 0; j < kelas[i].length; j++) {
                                    System.out.println("Kelas " + kelas[i][j] + ":");
                                    // Tampilkan nilai mahasiswa untuk kelas ini (jika ada)
                                    if (nilaiSiswa[i] != null) {
                                        for (int k = 0; k < nilaiSiswa[i].length; k++) {
                                            System.out.println("Nama mahasiswa " + (k + 1) + ": " + sc.nextLine());
                                            System.out.println("Total Nilai: " + nilaiSiswa[i][k]);
                                        }
                                    } else {
                                        System.out.println("Belum ada data nilai untuk kelas ini.");
                                    }
                                    System.out.println("------------------------------");
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
                        break; // Keluar dari loop utama jika dosen memilih untuk keluar
                    }
                }
            } else {
                System.out.println("Login Gagal. Silakan coba lagi.");
            }
        }

        sc.close();
    }

    private static boolean isValidKelas(String kelasTerpilih, String[] kelas) {
        for (String k : kelas) {
            if (k.equalsIgnoreCase(kelasTerpilih)) {
                return true;
            }
        }
        return false;
    }
}
