package main

import (
	"github.com/gin-gonic/gin"
	"net/http"
	"strings"

)
type TextRead struct {
	message string
}

func Main() {
	r := gin.Default()

	r.POST("/trello", PostTrello)
	r.POST("/slack", )
	r.POST("/todoist", )

	r.Run(":8080")
}

func PostTrello(c *gin.Context) {
	var text TextRead
	c.BindJSON(&text)
	s := strings.Split(text.message, "\n")
	for stringVar := range s{

	}
	c.JSON(http.StatusCreated, nil)
}
