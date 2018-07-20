package main

import (
	"encoding/csv"
	"fmt"
	"io"
	"net/http"
	"os"
)

func readCSVFromUrl(url string) ([][]string, error) {
	resp, err := http.Get(url)
	if err != nil {
		return nil, err
	}

	defer resp.Body.Close()
	reader := csv.NewReader(resp.Body)
	reader.Comma = ';'
	data, err := reader.ReadAll()
	if err != nil {
		return nil, err
	}

	return data, nil
}

func main() {

	file, err := os.Open("cidades.csv")
	fmt.Println("*******************INICIANDO***************")
	if err != nil {
		fmt.Println("Error:", err)
		return
	}

	defer file.Close()

	reader := csv.NewReader(file)
	reader.Comma = ';'
	lineCount := 0
	for {
		record, err := reader.Read()
		if err == io.EOF {
			break
		} else if err != nil {
			fmt.Println("Error:", err)
			return
		}

		fmt.Println("Record", lineCount, "is", record, "and has", len(record), "fields")

		for i := 0; i < len(record); i++ {
			fmt.Println(" ", record[i])
		}
		fmt.Println()
		lineCount += 1
		fmt.Println("*******************FIM DA LEITURA***************")
	}

	//BANCO
	fmt.Println("*******************SERVIDOR RODANDO NA 8080***************")
	a := App{}
	a.Initialize("root", "Debian23@", "amcom")
	a.Run(":8080")

}
