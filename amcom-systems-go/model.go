// model.go

package main

import (
	"database/sql"
	"fmt"
)

type city struct {
	ID               int     `json:"id"`
	IBGE             int     `json:"cd_ibge"`
	UF               string  `json:"nm_uf"`
	NAME             string  `json:"nm_city"`
	CAPITAL          bool    `json:"fg_capital"`
	LONGITUDE        float64 `json:"nu_longitude"`
	LATITUDE         float64 `json:"nu_latitute"`
	NOACCENTSNAME    string  `json:"nm_no_accents"`
	ALTERNATIVENAMES string  `json:"nm_alternative"`
	MICROREGION      string  `json:"nm_microregion"`
	MESOREGION       string  `json:"nm_mesoregion"`
	EXCLUDED         bool    `json:"fg_excluded"`
}

func (u *city) getCitys(db *sql.DB) error {
	statement := fmt.Sprintf("SELECT * FROM city WHERE id=%d", u.ID)
	return db.QueryRow(statement).Scan(
		&u.ID,
		&u.IBGE,
		&u.UF,
		&u.NAME,
		&u.CAPITAL,
		&u.LONGITUDE,
		&u.LATITUDE,
		&u.NOACCENTSNAME,
		&u.ALTERNATIVENAMES,
		&u.MICROREGION,
		&u.MESOREGION,
		&u.EXCLUDED)

}

func (u *city) getCity(db *sql.DB) error {
	statement := fmt.Sprintf("SELECT *FROM city WHERE id=%d", u.ID)
	return db.QueryRow(statement).Scan(&u.NAME)
}

/*
func (u *city) updateCity(db *sql.DB) error {
	statement := fmt.Sprintf("UPDATE city SET name='%s', age=%d WHERE id=%d", u.NAME, u.ID)
	_, err := db.Exec(statement)
	return err
}

func (u *city) deleteCity(db *sql.DB) error {
	statement := fmt.Sprintf("DELETE FROM citys WHERE id=%d", u.ID)
	_, err := db.Exec(statement)
	return err
}

func (u *city) createCity(db *sql.DB) error {
	statement := fmt.Sprintf("INSERT INTO citys(name, age) VALUES('%s', %d)", u.NAME)
	_, err := db.Exec(statement)

	if err != nil {
		return err
	}

	err = db.QueryRow("SELECT LAST_INSERT_ID()").Scan(&u.ID)

	if err != nil {
		return err
	}

	return nil
}

func getCitys(db *sql.DB, start, count int) ([]city, error) {
	//statement := fmt.Sprintf("SELECT id, name, age FROM citys LIMIT %d OFFSET %d", count, start)
	statement := fmt.Sprintf("SELECT id, name, age FROM citys")
	rows, err := db.Query(statement)

	if err != nil {
		return nil, err
	}

	defer rows.Close()

	citys := []city{}

	for rows.Next() {
		var u city
		if err := rows.Scan(&u.ID, &u.NAME); err != nil {
			return nil, err
		}
		citys = append(citys, u)
	}

	return citys, nil

}*/
