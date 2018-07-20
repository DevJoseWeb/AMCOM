// app.go

package main

import (
	"database/sql"
	"encoding/json"
	"fmt"
	"log"
	"net/http"
	"strconv"

	_ "github.com/go-sql-driver/mysql"
	"github.com/gorilla/mux"
)

type App struct {
	Router *mux.Router
	DB     *sql.DB
}

func (a *App) Initialize(user, password, dbname string) {
	connectionString := fmt.Sprintf("%s:%s@/%s", user, password, dbname)

	var err error
	a.DB, err = sql.Open("mysql", connectionString)
	if err != nil {
		log.Fatal(err)
	}

	a.Router = mux.NewRouter()
	a.initializeRoutes()
}

func (a *App) Run(addr string) {
	log.Fatal(http.ListenAndServe(addr, a.Router))
}

func (a *App) initializeRoutes() {
	//a.Router.HandleFunc("/citys", a.getCitys).Methods("GET")
	a.Router.HandleFunc("/city/{id:[0-9]+}", a.getCity).Methods("GET")
	/*a.Router.HandleFunc("/city", a.createCity).Methods("POST")
	a.Router.HandleFunc("/city/{id:[0-9]+}", a.updateCity).Methods("PUT")
	a.Router.HandleFunc("/city/{id:[0-9]+}", a.deleteCity).Methods("DELETE")*/
}

//GET
/*
func (a *App) getCitys(w http.ResponseWriter, r *http.Request) {
	count, _ := strconv.Atoi(r.FormValue("count"))
	start, _ := strconv.Atoi(r.FormValue("start"))

	if count > 10 || count < 1 {
		count = 10
	}
	if start < 0 {
		start = 0
	}

	city, err := getCitys(a.DB, start, count)
	if err != nil {
		respondWithError(w, http.StatusInternalServerError, err.Error())
		return
	}

	respondWithJSON(w, http.StatusOK, city)
}

/*
func (a *App) createCity(w http.ResponseWriter, r *http.Request) {
	var u city
	decoder := json.NewDecoder(r.Body)
	if err := decoder.Decode(&u); err != nil {
		respondWithError(w, http.StatusBadRequest, "Invalid request payload")
		return
	}
	defer r.Body.Close()

	if err := u.createCity(a.DB); err != nil {
		respondWithError(w, http.StatusInternalServerError, err.Error())
		return
	}

	respondWithJSON(w, http.StatusCreated, u)
}
*/
//GET
func (a *App) getCity(w http.ResponseWriter, r *http.Request) {
	vars := mux.Vars(r)
	id, err := strconv.Atoi(vars["id"])
	if err != nil {
		respondWithError(w, http.StatusBadRequest, "Esse ID não é válido  !")
		return
	}

	c := city{ID: id}
	if err := c.getCity(a.DB); err != nil {
		switch err {
		case sql.ErrNoRows:
			respondWithError(w, http.StatusNotFound, "City não encontrada !")
		default:
			respondWithError(w, http.StatusInternalServerError, err.Error())
		}
		return
	}

	respondWithJSON(w, http.StatusOK, a)
} /*
//UPDATE
		   func (a *App) updateCity(w http.ResponseWriter, r *http.Request) {
		   	vars := mux.Vars(r)
		   	id, err := strconv.Atoi(vars["id"])
		   	if err != nil {
		   		respondWithError(w, http.StatusBadRequest, "ID do Usuário Inválido")
		   		return
		   	}

		   	var u city
		   	decoder := json.NewDecoder(r.Body)
		   	if err := decoder.Decode(&u); err != nil {
		   		respondWithError(w, http.StatusBadRequest, "Carga útil de preenchimento inválida")
		   		return
		   	}
		   	defer r.Body.Close()
		   	u.ID = id

		   	if err := u.updateCity(a.DB); err != nil {
		   		respondWithError(w, http.StatusInternalServerError, err.Error())
		   		return
		   	}

		   	respondWithJSON(w, http.StatusOK, u)
		   }
//DELETE
		   func (a *App) deleteCity(w http.ResponseWriter, r *http.Request) {
		   	vars := mux.Vars(r)
		   	id, err := strconv.Atoi(vars["id"])
		   	if err != nil {
		   		respondWithError(w, http.StatusBadRequest, "ID do Usuário Inválido")
		   		return
		   	}

		   	u := user{ID: id}
		   	if err := u.deleteCity(a.DB); err != nil {
		   		respondWithError(w, http.StatusInternalServerError, err.Error())
		   		return
		   	}

		   	respondWithJSON(w, http.StatusOK, map[string]string{"result": "success"})
		   }
*/
//RESPOND WHITH
func respondWithError(w http.ResponseWriter, code int, message string) {
	respondWithJSON(w, code, map[string]string{"error": message})
}

//REPOND WITH JSON
func respondWithJSON(w http.ResponseWriter, code int, payload interface{}) {
	response, _ := json.Marshal(payload)

	w.Header().Set("Content-Type", "application/json")
	w.WriteHeader(code)
	w.Write(response)
}
