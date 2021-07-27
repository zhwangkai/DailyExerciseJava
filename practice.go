package main

import (
	"fmt"
	"io"
	"time"
	"os"
	"encoding/csv"
)


// Struct volunteer struct
type Volunteer struct {
	name string 
	age int  
	gender string  
	isParentJoin bool  
	taxYearlyPayment float64  
	title string  
	hasBrothers bool 
	noCrime bool 
	dateApply time.Time 
	score int 
}

const KB = 1024 * 1024
const MB = 1024 * KB
const GB = 1024 * MB

var fileName string = "volunteer.csv"

// To store the base 1000 volunteers
var vol_Base [1000]volunteer

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

	data := csv.NewReader(File)


	for {
        row, err := data.Read()
        if err != nil && err != io.EOF {
            fmt.Println("can not read, err is %+v", err)
        }
        if err == io.EOF {
            break
        }

        // Convert to Volunteer struct
        var vol = new(volunteer)
        err := Unmarshal(row, &vol)

        // Calculate the score
        vol.CalcScore()
        
        // Put the first 1000 volunteers to base 
        if len(vol_Base) <= 1000 {
        	vol_Base = append(vol_Base, vol)
        	continue
        }

        



    }
	fmt.Println("readAllBuff spend : ", time.Now().Sub(start1))

}

// Calculate score fn
func (v *Volunteer) CalcScore () {
	if v.isParentJoin {
		v.score += 500
	} else if v.taxYearlyPayment >= 1000000 && v.noCrime && v.hasBrothers {
		v.score += 300
	} else if strings.EqualFold(v.title,"engineer") {
		v.score += 200
	}
}

// Sort the volunteers based on the score and apply date
func Sort (volunteers []Volunteer) {
	for i := 1; i <= len(volunteers) - 1; i++ {
		for j :=i; j > 0; j-- {
			if volunteers[j-1].score < volunteers[j].score {
				volunteers[j-1], volunteers[j] = volunteers[j], volunteers[j-1]
			} else if volunteers[j-1].score == volunteers[j].score && volunteers[j-1].dateApply > volunteers[j].dateApply {
				volunteers[j-1], volunteers[j] = volunteers[j], volunteers[j-1]
			}
		}
	}
}

// Compare with and 1000 base Volunteers 
func Compare2BaseData (volunteer volunteer) {
	if volunteer.score > minVolunteer.score {
		vol_Base = append(vol_Base[:999], volunteer)
	} else if volunteer.score == minVolunteer.score && volunteer.dateApply < minVolunteer.dateApply {
		vol_Base = append(vol_Base[:999], volunteer)
	}
}
