package com.xinhui.mob201.data.repo

import android.util.Log
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.xinhui.mob201.core.TAG
import com.xinhui.mob201.data.model.Todo
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class TodosRepoFirestoreImpl(
    private val db: CollectionReference
): TodosRepo {
    override fun getAllTodos() = callbackFlow {
        val listener = db.addSnapshotListener { value, error ->
            if(error != null) {
                Log.d(TAG, error.message.toString())
                throw error
            }
            val todos = mutableListOf<Todo>()
            value?.documents?.let { items ->
                for(item in items) {
                    item.data?.let {
                        it["id"] = item.id
                        todos.add(Todo.fromHashMap(it))
                    }
                }
                trySend(todos)
            }
        }
        awaitClose {
            listener.remove()
        }
    }

    override suspend fun insert(todo: Todo) {
        db.add(todo.toHashMap()).await()
    }

    override suspend fun getTodo(id: String): Todo? {
        val snapshot = db.document(id).get().await()
        return snapshot.data?.let {
            it["id"] = snapshot.id
            Todo.fromHashMap(it)
        }
    }

    override suspend fun update(id: String, todo: Todo) {
        Log.d(TAG, "id: $id")
        db.document(id).set(todo.toHashMap()).await()
    }

    override suspend fun delete(id: String) {
        db.document(id).delete().await()
    }

    override suspend fun greet(): String {
        val publicDB = FirebaseFirestore.getInstance().collection("public")
        val snapshot = publicDB.document("greeting").get().await()
        return snapshot.data?.let {
                it["msg"].toString()
            } ?: "Hello user"
    }
}