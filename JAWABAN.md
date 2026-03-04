Latihan 1
Eksperimen 1: @Controller vs @RestController

/test/view → Tampil halaman HTML

/test/text → Tampil text langsung

Kesimpulan: @Controller buat view, @RestController buat text/json

Eksperimen 2: Template Tidak Ada

HTTP Status: 500

Error: Template tidak ditemukan

Eksperimen 3: @RequestParam vs @PathVariable
URL	Hasil
/greet	Hello, Guest!
/greet?name=Budi	Hello, Budi!
/greet/Andi	Hello, Andi!
Refleksi

@Controller → view, @RestController → text/json

Template hilang → error 500

@RequestParam dari query string, @PathVariable dari path URL

Latihan 2
Eksperimen 1: Fragment Tidak Ada

Error 500, fragment tidak ditemukan

Eksperimen 2: Static Resource

CSS/JS di static/ bisa diakses via URL

Salah path → 404

Refleksi

Static di resources/static supaya bisa diakses browser

Fragment buat reuse layout (navbar/footer)

Path salah → error render