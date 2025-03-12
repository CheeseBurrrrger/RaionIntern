package com.example.raionthings.data.remote.dto

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.storage.Storage
import kotlin.time.Duration.Companion.seconds

object supabase {
    val client: SupabaseClient = createSupabaseClient(
        supabaseUrl = "https://msnzexiwupgjzcchpigs.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Im1zbnpleGl3dXBnanpjY2hwaWdzIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDE3MDg5NzQsImV4cCI6MjA1NzI4NDk3NH0.zGfHeGp4Gb3caIFilhBmzPx5CyX0H8D2ZEeVrZ7IuVI"
    ) {
        install(Storage) {
            transferTimeout = 125.seconds // Default: 120 seconds
        }

    }
}