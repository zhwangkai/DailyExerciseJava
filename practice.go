package main

import (
	"fmt"
	// "bufio"
	"io"
	// "io/ioutil"
	"time"
	"os"
	"encoding/csv"
	// "encoding/json"
)


// Struct volunteer struct
type volunteer struct {
	name string `json:"name"`
	age int  `json:"age"`
	gender string  `json:"gender"`
	isParentJoin bool  `json:"isParentJoin"`
	taxYearlyPayment float64  `json:"taxYearlyPayment"`
	title string  `json:"title"`
	hasBrothers bool `json:"hasBrothers"`
	noCrime bool `json:"noCrime"`
	dateApply time.Time `json:"dateApply"`
	score int `json:"score"`
}

const KB = 1024 * 1024
const MB = 1024 * KB
const GB = 1024 * MB

var fileName string = "volunteer.csv"

func main() {
	ReadFile(fileName)
}

func ReadFile(fileName string) {
	start1 := time.Now()
	// Ready to read the local csv file
	// Open file
	File, err := os.Open(fileName)
	if err != nil {
		fmt.Println("Open file error: ", err)
		return
	}
	// Close file
	defer File.Close()

	// buffer := make([]byte, 6 * KB)
	// // 读取文件内容,并写入buffer中
	// data, err := File.Read(buffer)
	// if err != nil {
	// 	fmt.Println(err)
	// }
	// fmt.Println("data: ", data)
	// // 打印所有切片中的内容
	// fmt.Println(string(buffer[:data]))

	data := csv.NewReader(File)
	var vol = new(volunteer)
	for {
        row, err := data.Read()
        if err != nil && err != io.EOF {
            fmt.Println("can not read, err is %+v", err)
        }
        if err == io.EOF {
            break
        }

        // err = json.Unmarshal([]byte(row), &vol)
        // if err != nil {
        // 	return err
        // }
        JSON.stringify(row)
        fmt.Println(vol)
        fmt.Println(row)
    }
	fmt.Println("readAllBuff spend : ", time.Now().Sub(start1))

}