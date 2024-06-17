package com.example.air_tickets.domain

interface Repository {
    fun save()
    fun getData(): String
}